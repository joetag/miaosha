package com.example.miaosha.controller;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.example.miaosha.config.RabbitMQConfig;
import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.rabbitmq.MQSender;
import com.example.miaosha.rabbitmq.SeckillMessage;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderInfoService;
import com.example.miaosha.vo.GoodsVo;
import com.example.miaosha.vo.OrderVo;
import com.example.miaosha.vo.RespBean;
import com.example.miaosha.vo.RespBeanEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 秒杀
 *
 * @author kelin
 */
@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController implements InitializingBean {

    @Resource
    private SkOrderInfoService skOrderInfoService;

    @Autowired
    private SkGoodsService skGoodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MQSender sender;

    private Map<String, Boolean> isAddressedMap = new HashMap<>();


    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, HttpServletRequest request, HttpServletResponse response, SkUser user, Long goodsId) throws Exception{
        if (user == null) {
            return "login";
        }
        System.out.println("doSeckill");
        model.addAttribute("user", user);
        log.info("{}", user);
        GoodsVo goodsVo = skGoodsService.queryGoodsVoById(goodsId);
        model.addAttribute("goods", goodsVo);
        log.info("{}", goodsVo);
        //查询是否还有库存
        if (goodsVo.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.SECKILL_OVER.getMessage());
            return "seckillFail";
        }
        //查询是否有重复的订单
        SkOrderInfo skOrder = skOrderInfoService.getOrderByUserIdGoodsId(user.getId(), goodsId);
        log.info("{}", skOrder);
        if (skOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_SECKILL.getMessage());
            return "seckillFail";
        }
        SkOrderInfo orderInfo = skOrderInfoService.secKill(user, goodsVo);
        System.out.println("orderInfo");
        log.info("{}", orderInfo);

        model.addAttribute("orderInfo", orderInfo);
        return "orderDetail";

    }

    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(SkUser user, Long goodsId) throws Exception {

        System.out.println("doSeckill");
        if (user == null) {
            return RespBean.error(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        if (isAddressedMap.get("goods_stock" + goodsId.toString())) {
            return RespBean.error(RespBeanEnum.SECKILL_OVER);
        }

        SkOrderInfo skOrder = (SkOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        log.info("{}", skOrder);
        if (skOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_SECKILL);
        }

        Long redisStock = redisTemplate.opsForValue().decrement("goods_stock" + goodsId);

        if (redisStock == null) {
            return RespBean.error(RespBeanEnum.SERVER_ERROR);
        }

        log.info("The redisStock is {}", redisStock);
        if (redisStock <= 0) {
            afterPropertiesSet();
            Integer redisStock2 = (Integer) redisTemplate.opsForValue().get("goods_stock" + goodsId);
            if (redisStock2 != null && redisStock2 <= 0) {
                isAddressedMap.put("goods_stock" + goodsId, true);
                return RespBean.error(RespBeanEnum.SECKILL_OVER);
            }
        }

        sender.sendSeckillMessage(new SeckillMessage(user, goodsId));

        return new RespBean(200, "SUCCESS", null);
    }


    /**
     * 初始化讲物品信息加载进Redis
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = skGoodsService.queryAllGoodsVo();
        if (goodsVoList == null) {
            return;
        }
        for (GoodsVo goods :
                goodsVoList) {
            String tempKey = "goods_stock" + goods.getId().toString();
            redisTemplate.opsForValue().set(tempKey, goods.getStockCount());
            isAddressedMap.put(tempKey, false);
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public RespBean seckillResult(@Validated SkUser user, Long goodsId){
        if (user == null){
            return RespBean.error(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        System.out.println("The rusult goodsId is" + goodsId + " and the user.id is " + user.getId());
        SkOrderInfo orderInfo = (SkOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        int count = 0;
        while (orderInfo == null && count < RabbitMQConfig.MAX_QUERY_TIME){
            orderInfo = skOrderInfoService.getOrderByUserIdGoodsId(user.getId(), goodsId);
            count++;
        }
        if (orderInfo == null){
            orderInfo = (SkOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        }
        System.out.println("The counts is " + count);
        log.info("result.orderInfo {}", orderInfo);
        return RespBean.success(orderInfo);
    }
}
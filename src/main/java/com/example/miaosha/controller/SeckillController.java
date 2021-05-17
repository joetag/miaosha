package com.example.miaosha.controller;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.exception.GlobalException;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderInfoService;
import com.example.miaosha.service.SkOrderService;
import com.example.miaosha.vo.GoodsVo;
import com.example.miaosha.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 秒杀
 * @author kelin
 */
@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Autowired
    private SkOrderInfoService skOrderInfoService;

    @Autowired
    private SkGoodsService skGoodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;



    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, HttpServletRequest request, HttpServletResponse response, SkUser user, Long goodsId){
        if (user == null){
            return "login";
        }
        System.out.println("doSeckill");
        model.addAttribute("user", user);
        log.info("{}", user);
        GoodsVo goodsVo = skGoodsService.queryGoodsVoById(goodsId);
        model.addAttribute("goods", goodsVo);
        log.info("{}", goodsVo);
        //查询是否还有库存
        if (goodsVo.getStockCount() < 1){
            model.addAttribute("errmsg", RespBeanEnum.SECKILL_OVER.getMessage());
            return "seckillFail";
        }
        //查询是否有重复的订单
        SkOrderInfo skOrder = skOrderInfoService.getOrderByUserIdGoodsId(user.getId(), goodsId);
        log.info("{}", skOrder);
        if (skOrder != null){
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_SECKILL.getMessage());
            return "seckillFail";
        }
        SkOrderInfo orderInfo = skOrderInfoService.secKill(user, goodsVo);
        System.out.println("orderInfo");
        log.info("{}", orderInfo);
        model.addAttribute("orderInfo", orderInfo);

        return "orderDetail";
    }

}

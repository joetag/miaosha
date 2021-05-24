package com.example.miaosha.controller;

import com.example.miaosha.entity.SkGoods;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.vo.DetailVo;
import com.example.miaosha.vo.GoodsVo;

import com.example.miaosha.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * (SkGoods)表控制层
 *
 * @author makejava
 * @since 2021-05-06 14:58:33
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class SkGoodsController {
    /**
     * 服务对象
     */
    @Resource
    @Autowired
    private SkGoodsService skGoodsService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SkGoods selectOne(Long id) {
        return this.skGoodsService.queryById(id);
    }

    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, SkUser user, HttpServletRequest request, HttpServletResponse response) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //把html页面存进redis
        String html = (String) valueOperations.get("goodsList");
        //若果不为空
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        System.out.println("到Goods");
        model.addAttribute("user", user);
        model.addAttribute("goodsList", skGoodsService.queryAllGoodsVo());

        //如果不为空，手动渲染，存入redis
        IWebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", ctx);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }

        return html;
    }

    /**
     * 跳转商品详情页
     *
     * @param model
     * @param goodsId
     * @return
     */
    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(Model model, @PathVariable Long goodsId, SkUser user) {
        GoodsVo goods = skGoodsService.queryGoodsVoById(goodsId);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();

        //0代表秒杀没开始，1代表开始，2代表已经结束
        long seckillStatus = 0;
        long remainSeconds = 0;

        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime) {
            //秒杀还没有开始
            remainSeconds = (startTime - nowTime) / 1000;
        } else if (nowTime > endTime) {
            //秒杀已经结束
            seckillStatus = 2;
        } else {
            //秒杀开始
            seckillStatus = 1;
            remainSeconds = 0;
        }
        DetailVo detailView = new DetailVo();
        detailView.setGoodsVo(goods);
        detailView.setUser(user);
        detailView.setRemainSeconds(remainSeconds);
        detailView.setSecKillStatus(seckillStatus);
        return RespBean.success(detailView);
    }

    @RequestMapping(value = "/toDetail2/{goodsId}", produces = "text/html;charset=utf-8")
    public String toDetail2(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable Long goodsId, SkUser user) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsDetail" + goodsId);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("user", user);
        log.info("{}", user);
        GoodsVo goods = skGoodsService.queryGoodsVoById(goodsId);
        model.addAttribute("goods", goods);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();

        //0代表秒杀没开始，1代表开始，2代表已经结束
        long seckillStatus = 0;
        long remainSeconds = 0;

        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime) {
            //秒杀还没有开始
            remainSeconds = (startTime - nowTime) / 1000;
        } else if (nowTime > endTime) {
            //秒杀已经结束
            seckillStatus = 2;
        } else {
            //秒杀开始
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        IWebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", ctx);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsDetail" + goodsId, html, 60, TimeUnit.SECONDS);
        }
        return html;
    }
}
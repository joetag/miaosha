package com.example.miaosha.controller;

import com.example.miaosha.entity.SkUser;
import com.example.miaosha.rabbitmq.MQSender;
import com.example.miaosha.rabbitmq.SeckillMessage;
import com.example.miaosha.service.SkUserService;
import com.example.miaosha.vo.RespBean;
import com.example.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkUser)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
@RestController
@RequestMapping("/user")
public class SkUserController {
    /**
     * 服务对象
     */
    @Resource
    @Autowired
    private SkUserService skUserService;

    @Autowired
    private MQSender mqSender;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public SkUser selectOne(String id) {
        return this.skUserService.queryById(id);
    }

    /**
     * 用于测试
     * @param user
     * @return
     */
    @RequestMapping("/info")
    public RespBean info(SkUser user){
        return RespBean.success(user);
    }

    /**
     * 测试发送mq消息
     */
    @RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        SkUser user = new SkUser();
        user.setId("12");
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUser(user);
        seckillMessage.setGoodsId(2L);
        mqSender.sendSeckillMessage(seckillMessage);
    }

    /**
     * 测试发送mq消息
     */
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void mq1(){
        mqSender.send("hello");
    }

    @RequestMapping("/test")
    @ResponseBody
    public RespBean test(){
        return RespBean.success();
    }
}
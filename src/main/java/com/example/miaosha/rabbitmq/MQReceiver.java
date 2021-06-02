package com.example.miaosha.rabbitmq;


import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderInfoService;
import com.example.miaosha.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author kelin
 */
@Service
@Slf4j
public class MQReceiver {

    @Autowired
    SkOrderInfoService orderInfoService;

    @Autowired
    SkGoodsService goodsService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RabbitListener(queues = "topic.queue1")
    public void receive(SeckillMessage seckillMessage) {
        log.info("接受消息 {}", seckillMessage);
        //SeckillMessage seckillMessage = (SeckillMessage) msg;
        SkUser user = seckillMessage.getUser();
        Long goodsId = seckillMessage.getGoodsId();

        GoodsVo goods = goodsService.queryGoodsVoById(goodsId);

        if (goods.getStockCount() <= 0){
            return;
        }
        //客户端与redis服务器建立的连接
        SkOrderInfo order = (SkOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (order != null){
            return;
        }

        //写进MySQL
        orderInfoService.secKill(user, goods);
    }
    /**
    @RabbitListener(queues = "topic.queue2")
    public void receive2(Object msg) {
        log.info("QUEUE2 接受消息 {}", msg);
    }
    */
}

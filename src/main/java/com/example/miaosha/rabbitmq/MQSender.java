package com.example.miaosha.rabbitmq;

import com.example.miaosha.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kelin
 */
@Service
@Slf4j
public class MQSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg){
        log.info("消息发送状态: {}", msg);
        rabbitTemplate.convertAndSend("topicExchange", "topic.queue.red", msg + "1");
        rabbitTemplate.convertAndSend("topicExchange", "message.queue.green", msg + "2");
    }

    public void sendSeckillMessage(SeckillMessage seckillMessage){
        log.info("秒杀消息 {}", seckillMessage);
        rabbitTemplate.convertAndSend("topicExchange","seckillMessage.queue.yellow", seckillMessage);

    }
}

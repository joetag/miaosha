package com.example.miaosha.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author kelin
 */
@Configuration
public class RabbitMQConfig {

    private static final String TOPIC_QUEUE1 = "topic.queue1";
    private static final String TOPIC_QUEUE2 = "topic.queue2";
    private static final String TOPIC_EXCHANGE = "topicExchange";
    private static final String ROUTINGKEY1 = "#.queue.#";
    private static final String ROUTINGKEY2 = "*.queue.#";
    public static final Integer MAX_QUERY_TIME = 50;

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTINGKEY1);
    }


    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTINGKEY2);
    }
}

package com.example.miaosha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author kelin
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //Key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //Value的序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //HahKey的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //HahValue的序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        //注入链接工程
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}

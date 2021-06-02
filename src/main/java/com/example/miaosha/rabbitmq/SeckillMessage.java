package com.example.miaosha.rabbitmq;

import com.example.miaosha.entity.SkUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.amqp.core.Message;

import java.io.Serializable;

/**
 * @author kelin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeckillMessage implements Serializable  {

    private SkUser user;
    private Long goodsId;
}

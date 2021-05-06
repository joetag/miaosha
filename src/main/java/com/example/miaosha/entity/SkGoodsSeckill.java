package com.example.miaosha.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.io.Serializable;

/**
 * (SkGoodsSeckill)实体类
 *
 * @author makejava
 * @since 2021-05-06 15:07:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkGoodsSeckill implements Serializable {
    private static final long serialVersionUID = 254904437614643335L;
    /**
    * 秒杀商品id
    */
    private Long id;
    /**
    * 商品id
    */
    private Long goodsId;
    /**
    * 秒杀价
    */
    private Double seckillPrice;
    /**
    * 库存数量
    */
    private Integer stockCount;
    /**
    * 秒杀开始时间
    */
    private Date startDate;
    /**
    * 秒杀结束时间
    */
    private Date endDate;
    /**
    * 并发版本控制
    */
    private Integer version;



}
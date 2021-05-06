package com.example.miaosha.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.io.Serializable;

/**
 * (SkOrderInfo)实体类
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkOrderInfo implements Serializable {
    private static final long serialVersionUID = -80269208564858917L;
    
    private Long id;
    
    private Long userId;
    
    private Long goodsId;
    
    private Long deliveryAddrId;
    
    private String goodsName;
    
    private Integer goodsCount;
    
    private Double goodsPrice;
    /**
    * 订单渠道，1在线，2android，3ios
    */
    private Object orderChannel;
    /**
    * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
    */
    private Object status;
    
    private Date createDate;
    
    private Date payDate;



}
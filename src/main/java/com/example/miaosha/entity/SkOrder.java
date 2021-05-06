package com.example.miaosha.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (SkOrder)实体类
 *
 * @author makejava
 * @since 2021-05-06 15:08:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkOrder implements Serializable {
    private static final long serialVersionUID = 463252465061160169L;
    
    private Long id;
    
    private Long userId;
    
    private Long orderId;
    
    private Long goodsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

}
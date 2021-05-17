package com.example.miaosha.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (SkOrder)实体类
 *
 * @author makejava
 * @since 2021-05-06 15:08:12
 */
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class SkOrder implements Serializable {
    private static final long serialVersionUID = 463252465061160169L;
    
    private Long id;
    
    private String userId;
    
    private Long orderId;
    
    private Long goodsId;

}
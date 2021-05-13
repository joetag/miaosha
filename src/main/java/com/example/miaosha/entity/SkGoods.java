package com.example.miaosha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SkGoods)实体类
 *
 * @author makejava
 * @since 2021-05-06 14:58:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SkGoods implements Serializable {
    private static final long serialVersionUID = -73278331108151298L;
    /**
    * 商品ID
    */
    private Long id;
    /**
    * 商品名称
    */
    private String goodsName;
    /**
    * 商品标题
    */
    private String goodsTitle;
    /**
    * 商品图片
    */
    private String goodsImg;
    /**
    * 商品详情
    */
    private Object goodsDetail;
    
    private Double goodsPrice;
    /**
    * 商品库存，-1表示没有限制
    */
    private Integer goodsStock;

}
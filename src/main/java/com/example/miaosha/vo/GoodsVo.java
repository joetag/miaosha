package com.example.miaosha.vo;

import com.example.miaosha.entity.SkGoods;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kelin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodsVo extends SkGoods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}

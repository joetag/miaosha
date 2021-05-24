package com.example.miaosha.vo;

import com.example.miaosha.entity.SkOrderInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kelin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    GoodsVo goods;
    SkOrderInfo order;
}

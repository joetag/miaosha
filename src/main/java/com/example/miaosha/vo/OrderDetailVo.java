package com.example.miaosha.vo;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.entity.SkOrderInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详情返回对象
 * @author kelin
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {

    private GoodsVo goods;

    private SkOrderInfo order;
}

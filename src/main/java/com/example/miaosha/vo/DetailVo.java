package com.example.miaosha.vo;

import com.example.miaosha.entity.SkUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kelin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {
    private SkUser user;

    private GoodsVo goodsVo;

    private long secKillStatus;

    private long remainSeconds;
}

package com.example.miaosha.controller;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderInfoService;
import com.example.miaosha.vo.GoodsVo;
import com.example.miaosha.vo.OrderVo;
import com.example.miaosha.vo.RespBean;
import com.example.miaosha.vo.RespBeanEnum;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkOrderInfo)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
@RestController
@RequestMapping("/order")
public class SkOrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private SkOrderInfoService skOrderInfoService;

    @Autowired
    private SkGoodsService skGoodsService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SkOrderInfo selectOne(Long id) {
        return this.skOrderInfoService.queryById(id);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean getDetail(Long orderId){
        SkOrderInfo order = skOrderInfoService.queryById(orderId);
        GoodsVo goods = skGoodsService.queryGoodsVoById(order.getGoodsId());
        if (goods != null){
            OrderVo orderVo = new OrderVo();
            orderVo.setOrder(order);
            orderVo.setGoods(goods);
            return RespBean.success(orderVo);
        }
        return RespBean.error(RespBeanEnum.SERVER_ERROR);
    }

}
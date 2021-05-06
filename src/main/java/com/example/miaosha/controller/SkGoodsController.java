package com.example.miaosha.controller;

import com.example.miaosha.entity.SkGoods;
import com.example.miaosha.service.SkGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkGoods)表控制层
 *
 * @author makejava
 * @since 2021-05-06 14:58:33
 */
@RestController
@RequestMapping("skGoods")
public class SkGoodsController {
    /**
     * 服务对象
     */
    @Resource
    @Autowired
    private SkGoodsService skGoodsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SkGoods selectOne(Long id) {
        return this.skGoodsService.queryById(id);
    }

}
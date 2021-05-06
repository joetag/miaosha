package com.example.miaosha.controller;

import com.example.miaosha.entity.SkGoodsSeckill;
import com.example.miaosha.service.SkGoodsSeckillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkGoodsSeckill)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:07:12
 */
@RestController
@RequestMapping("skGoodsSeckill")
public class SkGoodsSeckillController {
    /**
     * 服务对象
     */
    @Resource
    private SkGoodsSeckillService skGoodsSeckillService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SkGoodsSeckill selectOne(Long id) {
        return this.skGoodsSeckillService.queryById(id);
    }

}
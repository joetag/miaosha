package com.example.miaosha.controller;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.service.SkOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkOrder)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:08:12
 */
@RestController
@RequestMapping("skOrder")
public class SkOrderController {
    /**
     * 服务对象
     */
    @Resource
    private SkOrderService skOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SkOrder selectOne(Long id) {
        return this.skOrderService.queryById(id);
    }

}
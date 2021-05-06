package com.example.miaosha.controller;

import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.service.SkOrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkOrderInfo)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
@RestController
@RequestMapping("/skOrderInfo")
public class SkOrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private SkOrderInfoService skOrderInfoService;

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

}
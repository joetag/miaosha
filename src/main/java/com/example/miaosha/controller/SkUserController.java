package com.example.miaosha.controller;

import com.example.miaosha.entity.SkUser;
import com.example.miaosha.service.SkUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SkUser)表控制层
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
@RestController
@RequestMapping("/skUser")
public class SkUserController {
    /**
     * 服务对象
     */
    @Resource
    private SkUserService skUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public SkUser selectOne(Object id) {
        return this.skUserService.queryById(id);
    }

}
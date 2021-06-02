package com.example.miaosha.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.miaosha.entity.SkGoodsSeckill;

import java.util.List;

/**
 * (SkGoodsSeckill)表服务接口
 *
 * @author makejava
 * @since 2021-05-06 15:07:11
 */
public interface SkGoodsSeckillService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkGoodsSeckill queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SkGoodsSeckill> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 实例对象
     */
    SkGoodsSeckill insert(SkGoodsSeckill skGoodsSeckill);

    /**
     * 修改数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 实例对象
     */
    SkGoodsSeckill update(SkGoodsSeckill skGoodsSeckill);

    /**
     * 安全修改
     *
     * @param skGoodsSeckill
     * @param updateWrapper
     * @return
     */
    int update(SkGoodsSeckill skGoodsSeckill, Wrapper<SkGoodsSeckill> updateWrapper);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
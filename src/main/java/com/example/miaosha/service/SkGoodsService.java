package com.example.miaosha.service;

import com.example.miaosha.entity.SkGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SkGoods)表服务接口
 *
 * @author makejava
 * @since 2021-05-06 14:58:33
 */

public interface SkGoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkGoods queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkGoods> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param skGoods 实例对象
     * @return 实例对象
     */
    SkGoods insert(SkGoods skGoods);

    /**
     * 修改数据
     *
     * @param skGoods 实例对象
     * @return 实例对象
     */
    SkGoods update(SkGoods skGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
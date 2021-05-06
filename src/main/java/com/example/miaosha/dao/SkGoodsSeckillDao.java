package com.example.miaosha.dao;

import com.example.miaosha.entity.SkGoodsSeckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SkGoodsSeckill)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-06 15:07:11
 */
@Mapper
public interface SkGoodsSeckillDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkGoodsSeckill queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkGoodsSeckill> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param skGoodsSeckill 实例对象
     * @return 对象列表
     */
    List<SkGoodsSeckill> queryAll(SkGoodsSeckill skGoodsSeckill);

    /**
     * 新增数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 影响行数
     */
    int insert(SkGoodsSeckill skGoodsSeckill);

    /**
     * 修改数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 影响行数
     */
    int update(SkGoodsSeckill skGoodsSeckill);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
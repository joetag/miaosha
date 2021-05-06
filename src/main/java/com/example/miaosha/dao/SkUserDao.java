package com.example.miaosha.dao;

import com.example.miaosha.entity.SkUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SkUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
@Mapper
public interface SkUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkUser queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param skUser 实例对象
     * @return 对象列表
     */
    List<SkUser> queryAll(SkUser skUser);

    /**
     * 新增数据
     *
     * @param skUser 实例对象
     * @return 影响行数
     */
    int insert(SkUser skUser);

    /**
     * 修改数据
     *
     * @param skUser 实例对象
     * @return 影响行数
     */
    int update(SkUser skUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}
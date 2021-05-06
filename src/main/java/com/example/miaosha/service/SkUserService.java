package com.example.miaosha.service;

import com.example.miaosha.entity.SkUser;
import java.util.List;

/**
 * (SkUser)表服务接口
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
public interface SkUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkUser queryById(Object id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param skUser 实例对象
     * @return 实例对象
     */
    SkUser insert(SkUser skUser);

    /**
     * 修改数据
     *
     * @param skUser 实例对象
     * @return 实例对象
     */
    SkUser update(SkUser skUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
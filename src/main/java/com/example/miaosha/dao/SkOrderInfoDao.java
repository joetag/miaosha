package com.example.miaosha.dao;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.entity.SkOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SkOrderInfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
@Mapper
public interface SkOrderInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkOrderInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkOrderInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param skOrderInfo 实例对象
     * @return 对象列表
     */
    List<SkOrderInfo> queryAll(SkOrderInfo skOrderInfo);

    /**
     * 新增数据
     *
     * @param skOrderInfo 实例对象
     * @return 影响行数
     */
    int insert(SkOrderInfo skOrderInfo);

    /**
     * 修改数据
     *
     * @param skOrderInfo 实例对象
     * @return 影响行数
     */
    int update(SkOrderInfo skOrderInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过用户id和物品id来查询订单是否重复
     * @param userId
     * @param goodsId
     * @return
     */
    SkOrderInfo getOrderByUserIdGoodsId(String userId, Long goodsId);

}
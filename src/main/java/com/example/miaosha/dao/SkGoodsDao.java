package com.example.miaosha.dao;

import com.example.miaosha.entity.SkGoods;
import com.example.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SkGoods)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-06 15:03:13
 */
@Mapper
public interface SkGoodsDao{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkGoods queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkGoods> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param skGoods 实例对象
     * @return 对象列表
     */
    List<SkGoods> queryAll(SkGoods skGoods);

    /**
     * 新增数据
     *
     * @param skGoods 实例对象
     * @return 影响行数
     */
    int insert(SkGoods skGoods);

    /**
     * 修改数据
     *
     * @param skGoods 实例对象
     * @return 影响行数
     */
    int update(SkGoods skGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询所所有的秒杀商品页面
     * @return
     */
    List<GoodsVo> queryAllGoodsVo();

    /**
     * 根据id来精确查询
     * @param goodsId
     * @return
     */
    GoodsVo queryGoodsVoById(Long goodsId);
}
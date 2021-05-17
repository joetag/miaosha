package com.example.miaosha.service;

import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.vo.GoodsVo;

import java.util.List;

/**
 * (SkOrderInfo)表服务接口
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
public interface SkOrderInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkOrderInfo queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SkOrderInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param skOrderInfo 实例对象
     * @return 实例对象
     */
    SkOrderInfo insert(SkOrderInfo skOrderInfo);

    /**
     * 修改数据
     *
     * @param skOrderInfo 实例对象
     * @return 实例对象
     */
    SkOrderInfo update(SkOrderInfo skOrderInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 通过用户id和物品id来查询订单是否重复
     * @param userId
     * @param goodsId
     * @return
     */
    SkOrderInfo getOrderByUserIdGoodsId(String userId, Long goodsId);

    /**
     * 实现秒杀
     * @param user
     * @param goodsVo
     * @return
     */
    SkOrderInfo secKill(SkUser user, GoodsVo goodsVo);

}
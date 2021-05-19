package com.example.miaosha.service.impl;

import com.example.miaosha.entity.*;
import com.example.miaosha.dao.SkOrderInfoDao;
import com.example.miaosha.exception.GlobalException;
import com.example.miaosha.service.SkGoodsSeckillService;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderInfoService;
import com.example.miaosha.service.SkOrderService;
import com.example.miaosha.vo.GoodsVo;
import com.example.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (SkOrderInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-05-06 15:10:08
 */
@Service("skOrderInfoService")
public class SkOrderInfoServiceImpl implements SkOrderInfoService {
    @Resource
    private SkOrderInfoDao skOrderInfoDao;

    @Autowired
    private SkGoodsService skGoodsService;

    @Autowired
    private SkOrderService skOrderService;

    @Autowired
    private SkGoodsSeckillService skGoodsSeckillService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkOrderInfo queryById(Long id) {
        return this.skOrderInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SkOrderInfo> queryAllByLimit(int offset, int limit) {
        return this.skOrderInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param skOrderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SkOrderInfo insert(SkOrderInfo skOrderInfo) {
        this.skOrderInfoDao.insert(skOrderInfo);
        return skOrderInfo;
    }

    /**
     * 修改数据
     *
     * @param skOrderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SkOrderInfo update(SkOrderInfo skOrderInfo) {
        this.skOrderInfoDao.update(skOrderInfo);
        return this.queryById(skOrderInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skOrderInfoDao.deleteById(id) > 0;
    }

    @Override
    public SkOrderInfo getOrderByUserIdGoodsId(String userId, Long goodsId) {
        return skOrderInfoDao.getOrderByUserIdGoodsId(userId, goodsId);
    }

    /**
     * 生成订单
     *
     * @param user
     * @param goodsVo
     * @return
     */
    @Override
    public SkOrderInfo secKill(SkUser user, GoodsVo goodsVo) {
        //更新商品信息
        SkGoods goods = skGoodsService.queryGoodsVoById(goodsVo.getId());
        SkGoodsSeckill goodsSeckill = skGoodsSeckillService.queryById(goodsVo.getId());
        if (goodsSeckill.getStockCount() > 1) {
            //goods.setGoodsStock(goods.getGoodsStock() - 1);
            goodsSeckill.setStockCount(goodsSeckill.getStockCount() - 1);
        } else {
            throw new GlobalException(RespBeanEnum.SECKILL_OVER);
        }
        //skGoodsService.update(goods);
        skGoodsSeckillService.update(goodsSeckill);
        //生成订单信息
        SkOrderInfo order = new SkOrderInfo();
        order.setGoodsId(goods.getId());
        order.setUserId(user.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        skOrderInfoDao.insert(order);

        //生成秒杀订单
        SkOrder skOrder = new SkOrder();
        skOrder.setOrderId(order.getId());
        skOrder.setGoodsId(goods.getId());
        skOrder.setUserId(user.getId());
        skOrderService.insert(skOrder);

        return order;
    }
}
package com.example.miaosha.service.impl;

import com.example.miaosha.entity.SkGoods;
import com.example.miaosha.entity.SkOrder;
import com.example.miaosha.dao.SkOrderDao;
import com.example.miaosha.entity.SkOrderInfo;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.exception.GlobalException;
import com.example.miaosha.service.SkGoodsService;
import com.example.miaosha.service.SkOrderService;
import com.example.miaosha.vo.GoodsVo;
import com.example.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * (SkOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-05-06 15:08:12
 */
@Service("skOrderService")
public class SkOrderServiceImpl implements SkOrderService {
    @Resource
    private SkOrderDao skOrderDao;

    @Autowired
    private SkGoodsService skGoodsService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkOrder queryById(Long id) {
        return this.skOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SkOrder> queryAllByLimit(int offset, int limit) {
        return this.skOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param skOrder 实例对象
     * @return 实例对象
     */
    @Override
    public SkOrder insert(SkOrder skOrder) {
        this.skOrderDao.insert(skOrder);
        return skOrder;
    }

    /**
     * 修改数据
     *
     * @param skOrder 实例对象
     * @return 实例对象
     */
    @Override
    public SkOrder update(SkOrder skOrder) {
        this.skOrderDao.update(skOrder);
        return this.queryById(skOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skOrderDao.deleteById(id) > 0;
    }

}
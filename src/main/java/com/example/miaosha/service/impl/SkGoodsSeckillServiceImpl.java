package com.example.miaosha.service.impl;

import com.example.miaosha.entity.SkGoodsSeckill;
import com.example.miaosha.dao.SkGoodsSeckillDao;
import com.example.miaosha.service.SkGoodsSeckillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SkGoodsSeckill)表服务实现类
 *
 * @author makejava
 * @since 2021-05-06 15:07:12
 */
@Service("skGoodsSeckillService")
public class SkGoodsSeckillServiceImpl implements SkGoodsSeckillService {
    @Resource
    private SkGoodsSeckillDao skGoodsSeckillDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkGoodsSeckill queryById(Long id) {
        return this.skGoodsSeckillDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SkGoodsSeckill> queryAllByLimit(int offset, int limit) {
        return this.skGoodsSeckillDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 实例对象
     */
    @Override
    public SkGoodsSeckill insert(SkGoodsSeckill skGoodsSeckill) {
        this.skGoodsSeckillDao.insert(skGoodsSeckill);
        return skGoodsSeckill;
    }

    /**
     * 修改数据
     *
     * @param skGoodsSeckill 实例对象
     * @return 实例对象
     */
    @Override
    public SkGoodsSeckill update(SkGoodsSeckill skGoodsSeckill) {
        this.skGoodsSeckillDao.update(skGoodsSeckill);
        return this.queryById(skGoodsSeckill.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skGoodsSeckillDao.deleteById(id) > 0;
    }
}
package com.example.miaosha.service.impl;

import com.example.miaosha.entity.SkGoods;
import com.example.miaosha.dao.SkGoodsDao;
import com.example.miaosha.service.SkGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SkGoods)表服务实现类
 *
 * @author makejava
 * @since 2021-05-06 14:58:33
 */
@Service("skGoodsService")
public class SkGoodsServiceImpl implements SkGoodsService {
    @Resource
    private SkGoodsDao skGoodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkGoods queryById(Long id) {
        return this.skGoodsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SkGoods> queryAllByLimit(int offset, int limit) {
        return this.skGoodsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param skGoods 实例对象
     * @return 实例对象
     */
    @Override
    public SkGoods insert(SkGoods skGoods) {
        this.skGoodsDao.insert(skGoods);
        return skGoods;
    }

    /**
     * 修改数据
     *
     * @param skGoods 实例对象
     * @return 实例对象
     */
    @Override
    public SkGoods update(SkGoods skGoods) {
        this.skGoodsDao.update(skGoods);
        return this.queryById(skGoods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skGoodsDao.deleteById(id) > 0;
    }
}
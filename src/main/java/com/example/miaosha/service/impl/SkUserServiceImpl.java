package com.example.miaosha.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.dao.SkUserDao;
import com.example.miaosha.exception.GlobalException;
import com.example.miaosha.service.SkUserService;
import com.example.miaosha.utils.CookieUtil;
import com.example.miaosha.utils.MD5Util;
import com.example.miaosha.utils.UUIDUtil;
import com.example.miaosha.utils.isMobile;
import com.example.miaosha.vo.LoginVo;
import com.example.miaosha.vo.RespBean;
import com.example.miaosha.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * (SkUser)表服务实现类
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
@Service("skUserService")
public class SkUserServiceImpl implements SkUserService {

    @Autowired
    private SkUserDao skUserDao;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkUser queryById(String id) {
        return this.skUserDao.queryById(id);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SkUser> queryAllByLimit(int offset, int limit) {
        return this.skUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param skUser 实例对象
     * @return 实例对象
     */
    @Override
    public SkUser insert(SkUser skUser) {
        this.skUserDao.insert(skUser);
        return skUser;
    }

    /**
     * 修改数据
     *
     * @param skUser 实例对象
     * @return 实例对象
     */
    @Override
    public SkUser update(SkUser skUser) {
        this.skUserDao.update(skUser);
        return this.queryById(skUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.skUserDao.deleteById(id) > 0;
    }

    /**
     * 典型缓存同步场景：更新密码
     * 删除redis然后重新存进新的对象
     * 让MySQL和Redis中的数据同
     */
    @Override
    public RespBean updatePassword(String usertTocket, Long id, String passWord, HttpServletResponse response, HttpServletRequest request) {
        SkUser user = this.getUserByCookie(response,usertTocket);
        if (user == null){
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToFromPass(passWord));
        int res = skUserDao.update(user);
        if (res == 1){
            redisTemplate.delete("user" + usertTocket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_ERROR);
    }

    /**
     * 登陆信息
     *
     * @param loginVo 登陆信息
     * @param response
     * @param request
     * @return 返回类型
     */
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletResponse response, HttpServletRequest request) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_EMPTY);
        }
        if (!isMobile.mobileMatch(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
        if (StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.PASSWORD_EMPTY);
        }

        SkUser user = skUserDao.queryById(mobile);
        if (null == user) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.PASSWORD_ERROR);
        }
        String ticket = UUIDUtil.getUUID();
        //这里添加时间会报错
        redisTemplate.opsForValue().set("user"+ticket, user);
        CookieUtil.addCookie(response,"userTicket", ticket, 60);
        return RespBean.success();
    }

    @Override
    public SkUser getUserByCookie(HttpServletResponse response, String userTicket) {
        if (StringUtils.isEmpty(userTicket)){return null;}
        SkUser user = (SkUser) redisTemplate.opsForValue().get("user" + userTicket);
        if (user == null){
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }else {
            CookieUtil.addCookie(response, "userTicket", userTicket, 100);
        }
        return user;
    }

    @Override
    public boolean saveBatch(Collection<SkUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SkUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<SkUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(SkUser entity) {
        return false;
    }

    @Override
    public SkUser getOne(Wrapper<SkUser> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<SkUser> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<SkUser> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<SkUser> getBaseMapper() {
        return null;
    }

    @Override
    public Class<SkUser> getEntityClass() {
        return null;
    }
}
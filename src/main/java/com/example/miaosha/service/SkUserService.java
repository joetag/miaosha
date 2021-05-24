package com.example.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.miaosha.entity.SkUser;
import com.example.miaosha.vo.LoginVo;
import com.example.miaosha.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (SkUser)表服务接口
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
public interface SkUserService extends IService<SkUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkUser queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
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

    /**
     * 更新密码
     *
     * @param usertTocket
     * @param id
     * @param passWord
     * @param response
     * @param request
     * @return
     */

    RespBean updatePassword(String usertTocket, Long id, String passWord, HttpServletResponse response, HttpServletRequest request);

    /**
     * 登陆信息
     *
     * @param loginVo  登陆信息
     * @param response
     * @param request
     * @return 返回类型
     */

    RespBean doLogin(LoginVo loginVo, HttpServletResponse response, HttpServletRequest request);

    /**
     * 根据cookie获取用户
     *
     * @param userTicket
     * @param response
     * @return
     */
    SkUser getUserByCookie(HttpServletResponse response, String userTicket);
}
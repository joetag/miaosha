package com.example.miaosha.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.io.Serializable;

/**
 * (SkUser)实体类
 *
 * @author makejava
 * @since 2021-05-06 15:13:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkUser implements Serializable {
    private static final long serialVersionUID = -77252011356094272L;
    /**
    * 用户id
    */
    private Object id;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * MD5(MD5(pass明文+固定salt)+salt
    */
    private String password;
    /**
    * 混淆盐
    */
    private String salt;
    /**
    * 头像，云存储的ID
    */
    private String head;
    /**
    * 注册时间
    */
    private Date registerDate;
    /**
    * 上次登录时间
    */
    private Date lastLoginDate;
    /**
    * 登录次数
    */
    private Integer loginCount;

}
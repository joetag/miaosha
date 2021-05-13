package com.example.miaosha.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 * @author kelin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private Integer code;
    private String message;
    private Object obj;

    /**
     *
     * @return 成功返回的类型
     */
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     *
     * @param obj 一个对象
     * @return 成功返回的类型
     */
    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), obj);
    }

    /**
     *
     * @param respBeanEnum 返回对象的枚举类型
     * @return 返回失败
     */
    public static RespBean error(RespBeanEnum respBeanEnum){
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(), null);
    }

    /**
     *
     * @param respBeanEnum 返回对象的枚举类型
     * @param obj 对象
     * @return 返回失败
     */
    public static RespBean error(RespBeanEnum respBeanEnum, Object obj){
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(), obj);
    }
}

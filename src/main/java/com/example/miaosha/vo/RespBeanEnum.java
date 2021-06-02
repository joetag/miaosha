package com.example.miaosha.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kelin
 */

@ToString
@AllArgsConstructor
@Getter
public enum RespBeanEnum {
    /**
     * 成功返回的类型
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常+我看看是哪里异常1"),

    /**
     * 失败的类型
     */
    SERVER_ERROR(500100, "服务端异常+我看看是哪里异常2"),
    BIND_ERROR(500101, "参数校验异常：%s"),
    ACCESS_LIMIT_REACHED(500104, "访问高峰期，请稍等！"),

    SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"),
    MOBILE_EMPTY(500212, "手机号不能为空"),
    MOBILE_ERROR(500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    PRIMARY_ERROR(500216, "主键冲突"),

    ORDER_NOT_EXIST(500400, "订单不存在"),

    //秒杀模块 5005XX
    SECKILL_OVER(500500, "商品已经秒杀完毕"),
    REPEATE_SECKILL(500501, "不能重复秒杀");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 错误信息
     */
    private final String message;

    public Integer getCode() {
        return code;
    }
}

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
public enum RespBeanEnum {
    /**
     * 成功返回的类型
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常");
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

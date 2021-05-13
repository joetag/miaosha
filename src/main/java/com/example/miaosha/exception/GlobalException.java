package com.example.miaosha.exception;


import com.example.miaosha.vo.RespBean;
import com.example.miaosha.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kelin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException implements Serializable {

    private RespBeanEnum respBeanEnum;
}
package com.example.miaosha.utils;

import java.util.UUID;

/**
 * 随机生成uuid
 * @author kelin
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

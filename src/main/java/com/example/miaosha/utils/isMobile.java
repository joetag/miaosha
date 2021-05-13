package com.example.miaosha.utils;




import org.thymeleaf.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author kelin
 */
public class isMobile {
    /**
     * 校验手机是否合规 2020年最全的国内手机号格式
     */
    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";

    /**
     * 校验手机号
     *
     * @param mobile 手机号
     * @return boolean true:是  false:否
     */
    public static boolean mobileMatch(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}

package com.example.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String SALT = "1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str = SALT.charAt(2)+SALT.charAt(3)+inputPass+SALT.charAt(5)+SALT.charAt(7);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt){
        String str = salt.charAt(2)+salt.charAt(3)+formPass+salt.charAt(5)+salt.charAt(7);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt){
        String fromPass = inputPassToFromPass(inputPass);
        return formPassToDBPass(fromPass, salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
        System.out.println(inputPassToFromPass("123456"));
        System.out.println(formPassToDBPass("24cd1b5f0d4b274dec37fb7d273db768", "1a2b3c4d"));
    }
}

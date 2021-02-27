package com.example.demo.common.utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    /**
     * MD5方法加密
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text){
        //加密后的字符串
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

}
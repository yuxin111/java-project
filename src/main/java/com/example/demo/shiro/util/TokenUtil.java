package com.example.demo.shiro.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * token工具类
 */
public class TokenUtil {
    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {

        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (!StringUtils.hasText(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }
}

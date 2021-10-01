package com.example.demo.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.annotation.AllowDevPass;
import com.example.demo.common.enums.CommonEnum;
import com.example.demo.common.utils.IPUtils;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.shiro.util.HttpContextUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/10/1
 **/
@Component
public class IpInterceptor implements HandlerInterceptor {
    @Value("${whitelist}")
    private String whitelistStr;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 预请求允许通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String ipAddr = IPUtils.getRealIP(request);
        ArrayList<String> whitelist = new ArrayList<>();
        Collections.addAll(whitelist, whitelistStr.split(","));
        if (whitelist.contains(ipAddr)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AllowDevPass.class)) {
                response.setContentType("application/json;charset=utf-8");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
                response.setCharacterEncoding("UTF-8");
                ResultBody resultBody = new ResultBody(CommonEnum.DEV_NOT_ALLOW_PASS.getResultCode());
                resultBody.setMessage(CommonEnum.DEV_NOT_ALLOW_PASS.getResultMsg());
                String json = JSONObject.toJSONString(resultBody);
                response.getWriter().print(json);
            } else {
                return true;
            }
        }
        return false;
    }
}

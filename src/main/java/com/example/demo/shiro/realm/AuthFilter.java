package com.example.demo.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.shiro.util.HttpContextUtil;
import com.example.demo.shiro.util.TokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token
        String token = TokenUtil.getRequestToken((HttpServletRequest) servletRequest);
        return new AuthToken(token);
    }
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
//            return true;
//        }
//        return false;
//    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token，如果token不存在，直接返回
        String token = TokenUtil.getRequestToken((HttpServletRequest) servletRequest);
        if (!StringUtils.hasText(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            ResultBody resultBody = new ResultBody(403);
            resultBody.setMessage("请先登录");
            String json = JSONObject.toJSONString(resultBody);
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ResultBody resultBody = new ResultBody(403);
            if (e instanceof IncorrectCredentialsException) {
                resultBody.setMessage("登录凭证已失效，请重新登录");
            } else if (e instanceof UnknownAccountException) {
                resultBody.setMessage("登录用户不存在，请重新选择用户");
            }else{
                resultBody.setMessage("登录发生异常，请联系管理员");
            }
            String json = JSONObject.toJSONString(resultBody);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
        }
        return false;
    }
}

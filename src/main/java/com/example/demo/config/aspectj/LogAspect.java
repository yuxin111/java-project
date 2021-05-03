package com.example.demo.config.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.enums.BusinessStatus;
import com.example.demo.common.utils.ShiroUtils;
import com.example.demo.common.utils.ThreadManager;
import com.example.demo.config.async.AsyncTask;
import com.example.demo.config.executor.ExecutorConfig;
import com.example.demo.core.entity.SysOperLog;
import com.example.demo.core.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志记录处理
 *
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private AsyncTask asyncTask;

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    // 配置织入点
    @Pointcut("@annotation(com.example.demo.common.annotation.MyLog)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            MyLog myLog = getAnnotationLog(joinPoint);
            if (myLog == null) {
                return;
            }

            SysOperLog operLog = new SysOperLog();

            SysUser user = ShiroUtils.getSysUser();
            operLog.setUserId(user.getUserId());

            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            operLog.setCreateTime(new Date());
            operLog.setOperLog(myLog.value());
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult),0,2000));

            if (e != null) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(),0,2000));
            }

            asyncTask.recordOperLog(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==记录本地异常日志异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private MyLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(MyLog.class);
        }
        return null;
    }
}

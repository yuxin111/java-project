package com.example.demo.config.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.example.demo.common.annotation.MyLog;
import com.example.demo.common.utils.ThreadManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(com.example.demo.config.aspectj.LogAspect.class);

    /** 排除敏感属性字段 */
    public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

    // 配置织入点
    @Pointcut("@annotation(com.example.demo.common.annotation.MyLog)")
    public void logPointCut()
    {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * 
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // 获得注解
            MyLog myLog = getAnnotationLog(joinPoint);
            if (myLog == null)
            {
                return;
            }
            ThreadManager.getThreadPollProxy().execute(new Runnable() {
                @Override
                public void run() {
                    
                }
            });

//            // 获取当前的用户
//            SysUser currentUser = ShiroUtils.getSysUser();
//
//            // *========数据库日志=========*//
//            SysOperLog operLog = new SysOperLog();
//            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
//            // 请求的地址
//            String ip = ShiroUtils.getIp();
//            operLog.setOperIp(ip);
//            // 返回参数
//            operLog.setJsonResult(StringUtils.substring(JSON.marshal(jsonResult), 0, 2000));
//
//            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
//            if (currentUser != null)
//            {
//                operLog.setOperName(currentUser.getLoginName());
//                if (StringUtils.isNotNull(currentUser.getDept())
//                        && StringUtils.isNotEmpty(currentUser.getDept().getDeptName()))
//                {
//                    operLog.setDeptName(currentUser.getDept().getDeptName());
//                }
//            }
//
//            if (e != null)
//            {
//                operLog.setStatus(BusinessStatus.FAIL.ordinal());
//                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
//            }
//            // 设置方法名称
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            operLog.setMethod(className + "." + methodName + "()");
//            // 设置请求方式
//            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
//            // 处理设置注解上的参数
//            getControllerMethodDescription(controllerLog, operLog);
//            // 保存数据库
//            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private MyLog getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(MyLog.class);
        }
        return null;
    }
}

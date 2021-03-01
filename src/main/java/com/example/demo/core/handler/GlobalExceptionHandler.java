package com.example.demo.core.handler;

import com.example.demo.config.exception.MyException;
import com.example.demo.core.entity.ResultBody;
import com.example.demo.enums.CommonEnum;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = MyException.class)
    public ResultBody myExceptionHandler(HttpServletRequest req, MyException e) {
        logger.error("发生业务异常！原因是：" , e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是：" , e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        if (e instanceof DuplicateKeyException) {
            Throwable throwable = e.getCause();
            if (throwable instanceof SQLIntegrityConstraintViolationException) {
                logger.error("SQL有重复键值！原因是：" , e);
                return ResultBody.error("有重复的键值，请检查");
            }
        }
        logger.error("未知异常！原因是：" , e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}
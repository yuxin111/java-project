package com.example.demo.common.annotation;

import com.example.demo.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * 
 * @author yuxin
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog
{
    /**
     * 模块 
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

}

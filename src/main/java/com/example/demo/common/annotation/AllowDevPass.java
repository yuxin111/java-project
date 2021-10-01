package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 允许在开发环境查看
 * 
 * @author yuxin
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowDevPass
{
    /**
     * 模块 
     */
    public String value() default "";

}

package com.zzc.aspectjdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${运行时权限aop拦截注解}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionIntercept {
    String[] permissions();
}

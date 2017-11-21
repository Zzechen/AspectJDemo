package com.zzc.aspectjdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auth zzc
 * @date 2017/11/21
 * @desc ${属性取值拦截注解}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FiledGetIntercept {
}

package com.zzc.aspectjdemo.model;

import com.zzc.aspectjdemo.annotation.ConstructorIntercept;

/**
 * @auth zzc
 * @date 2017/11/21
 * @desc ${通过aop，拦截构造器}
 */

public class AopConstructorModel {

    @ConstructorIntercept
    public AopConstructorModel() {
    }
}

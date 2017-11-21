package com.zzc.aspectjdemo.model;

import com.zzc.aspectjdemo.annotation.FiledGetIntercept;
import com.zzc.aspectjdemo.annotation.FiledSetIntercept;
import com.zzc.aspectjdemo.utils.TimeUtils;

/**
 * @auth zzc
 * @date 2017/11/21
 * @desc ${通过Aop，set/get属性值}
 */

public class AopFiledModel {
    @FiledGetIntercept
    @FiledSetIntercept
    private String time;//赋值时间

    public AopFiledModel() {
        time = TimeUtils.getCurTimeString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

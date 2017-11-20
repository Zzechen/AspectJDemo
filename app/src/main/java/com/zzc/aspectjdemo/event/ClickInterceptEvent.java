package com.zzc.aspectjdemo.event;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${拦截click事件event}
 */

public class ClickInterceptEvent {
    private String info;

    public ClickInterceptEvent(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

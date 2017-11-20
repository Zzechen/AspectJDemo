package com.zzc.aspectjdemo.aop;

import com.zzc.aspectjdemo.event.ClickInterceptEvent;
import com.zzc.aspectjdemo.utils.TimeUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${点击事件aop}
 */
@Aspect
public class ClickAop {
    private static final String TAG = ClickAop.class.toString();
    private static final int VIEW_CLICK_DURATION = 2000;

    private HashMap<String,Long> mTimeMap = new HashMap<>();

    @Around("execution(* android.view.View.OnClickListener.onClick(..)) && !@annotation(com.zzc.aspectjdemo.annotation.ClickFilter)")
    public Object clickAop(ProceedingJoinPoint point) {
        long curTimeMills = TimeUtils.getCurTimeMills();
        String key = point.toString();
        Long lastTime = mTimeMap.get(key);
        if (lastTime != null) {
            if (curTimeMills - lastTime > VIEW_CLICK_DURATION) {//
                mTimeMap.put(key,curTimeMills);
                return proceed(point);
            } else {
                EventBus.getDefault().post(new ClickInterceptEvent("intercept click event at " + curTimeMills));
                return null;
            }
        } else {
            mTimeMap.put(key,curTimeMills);
            return proceed(point);
        }
    }

    private Object proceed(ProceedingJoinPoint point) {
        Object obj = null;
        try {
            obj = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            return obj;
        }
    }
}

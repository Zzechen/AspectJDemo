package com.zzc.aspectjdemo.aop;

import com.zzc.aspectjdemo.event.AspectjEvent;
import com.zzc.aspectjdemo.utils.TimeUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.greenrobot.eventbus.EventBus;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${所有注释使用}
 */
@Aspect
public class AllAop {

    /**
     * 指令：call
     * 切入位置：添加{@link com.zzc.aspectjdemo.annotation.CallMethod}注解的所有方法的调用点
     *
     * @param point
     * @return
     */
    @Around("call(* *.*(..)) && @annotation(com.zzc.aspectjdemo.annotation.CallMethod)")
    public Object callMethod(ProceedingJoinPoint point) {
        Object result = null;
        try {
            EventBus.getDefault().post(new AspectjEvent("around call click:" + TimeUtils.getCurTimeString()));
            result = point.proceed(point.getArgs());
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 拦截属性赋值
     * todo
     * 拦截反射属性赋值
     *
     * @param point
     * @return
     */
    @Around("set(private * *) && @annotation(com.zzc.aspectjdemo.annotation.FiledSetIntercept)")
    public Object setFiledMethod(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            String newValue = "hahahhahahahhaahahaha";
            args[0] = newValue;
            EventBus.getDefault().post(new AspectjEvent("around set filed click:\nnewValue:" + newValue));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 拦截属性取值
     * todo
     * 拦截反射取值
     *
     * @param point
     * @return
     */
    @Around("get(private * *) && @annotation(com.zzc.aspectjdemo.annotation.FiledGetIntercept)")
    public Object getFiledMethod(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            EventBus.getDefault().post(new AspectjEvent("around get filed click:"));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 以调用构造器的位置为切入点
     * todo
     * 反射调用位置
     *
     * @param point
     * @return
     */
    @Around("call(*.new(..)) && @annotation(com.zzc.aspectjdemo.annotation.ConstructorIntercept)")
    public Object callConstructor(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            EventBus.getDefault().post(new AspectjEvent("around call constructor click:\n time:" + TimeUtils.getCurTimeString()));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 切入构造器
     * todo 反射
     *
     * @param point
     * @return
     */
    @Around("execution(*.new(..)) && @annotation(com.zzc.aspectjdemo.annotation.ConstructorIntercept)")
    public Object executionConstructor(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            EventBus.getDefault().post(new AspectjEvent("around execution constructor click:\n time:" + TimeUtils.getCurTimeString()));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 对象初始化切入点，todo 未通过
     *
     * @param point
     * @return
     */
    @Around("initialization(com.zzc.aspectjdemo.model.InitConstructorModel.new(..))")
    public Object initConstructor(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            EventBus.getDefault().post(new AspectjEvent("around initialization constructor click:\n time:" + TimeUtils.getCurTimeString()));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }

    /**
     * 未通过 todo
     * 捕获异常
     *
     * @param point
     * @return
     */
    @Around("handler(java.lang.ArithmeticException)")
    public Object handlerEx(ProceedingJoinPoint point) {
        Object result = null;
        Object[] args = point.getArgs();
        try {
            EventBus.getDefault().post(new AspectjEvent("around handler exception click:\nex:" + "\n time:" + TimeUtils.getCurTimeString()));
            result = point.proceed(args);
        } catch (Throwable throwable) {
        } finally {
            return result;
        }
    }
}

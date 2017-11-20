package com.zzc.aspectjdemo.aop;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zzc.aspectjdemo.listener.PermissionResultListener;
import com.zzc.aspectjdemo.ui.PermissionFragment;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.ref.WeakReference;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${运行时权限aop}
 */
@Aspect
public class PermissionAop {
    private static final String TAG_FRAGMENT = "permissionFragment";

    @Around("execution (* *.*(..)) && @annotation(com.zzc.aspectjdemo.annotation.PermissionIntercept)")
    public Object permissionAop(final ProceedingJoinPoint point) {
        FragmentActivity activity = null;
        String permission = null;
        Object[] args = point.getArgs();
        if (args != null) {
            for (Object obj : args) {
                if (obj instanceof FragmentActivity) {
                    activity = (FragmentActivity) obj;
                    continue;
                }
                if (obj instanceof String) {
                    permission = (String) obj;
                }
            }
        }
        if (activity != null && permission != null) {
            int result = ActivityCompat.checkSelfPermission(activity, permission);
            if (PackageManager.PERMISSION_GRANTED != result) {
                final WeakHandler handler = new WeakHandler(activity, Looper.getMainLooper());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                PermissionFragment fragment = (PermissionFragment) fragmentManager.findFragmentByTag(TAG_FRAGMENT);
                if (fragment == null) {
                    fragment = new PermissionFragment();
                    fragment.setResultListener(new PermissionResultListener() {
                        @Override
                        public void result(boolean granted) {
                            if (granted) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        proceed(point);
                                    }
                                });
                            }
                        }
                    });
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(fragment, TAG_FRAGMENT).commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
                fragment.request(permission);
            }else {
                return proceed(point);
            }
        } else {
            return null;
        }
        return null;
    }

    public static class WeakHandler extends Handler {
        private WeakReference<Activity> mActivity;

        public WeakHandler(Activity activity, Looper looper) {
            super(looper);
            mActivity = new WeakReference<>(activity);
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

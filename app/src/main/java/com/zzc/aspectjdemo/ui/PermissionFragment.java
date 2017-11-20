package com.zzc.aspectjdemo.ui;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.zzc.aspectjdemo.listener.PermissionResultListener;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${申请权限}
 */

public class PermissionFragment extends Fragment {
    private static final String TAG = PermissionFragment.class.toString();
    private static final int REQ = 0x11;
    private PermissionResultListener mResultListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQ) return;
        if (mResultListener != null) {
            mResultListener.result(PackageManager.PERMISSION_GRANTED == grantResults[0]);
        }
    }

    public void setResultListener(PermissionResultListener listener) {
        this.mResultListener = listener;
    }

    public void request(String permission) {
        if (isAdded()){
            requestPermissions(new String[]{permission}, REQ);
        }else {
            Log.e(TAG, "request: fragment has not be added");
        }
    }
}

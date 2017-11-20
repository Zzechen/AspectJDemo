package com.zzc.aspectjdemo.ui;

import android.Manifest;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.annotation.ClickFilter;
import com.zzc.aspectjdemo.annotation.PermissionIntercept;
import com.zzc.aspectjdemo.databinding.ActivityPermissionBinding;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${desc}
 */

public class PermissionActivity extends AppCompatActivity {

    private ActivityPermissionBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_permission);
        mBinding.btnPermissionCamera.setOnClickListener(new View.OnClickListener() {
            @ClickFilter
            @Override
            public void onClick(View v) {
                camera(PermissionActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }

    @PermissionIntercept(permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    private void camera(Activity activity,String permission) {
        Toast.makeText(this, "WRITE_EXTERNAL_STORAGE permission is granted", Toast.LENGTH_SHORT).show();
    }
}

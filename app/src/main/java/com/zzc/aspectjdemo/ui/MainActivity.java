package com.zzc.aspectjdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btnClickAop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ClickAopActivity.class);
            }
        });
        mBinding.btnPermissionAop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(PermissionActivity.class);
            }
        });
        mBinding.btnAllAop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AllAopActivity.class);
            }
        });
    }

    private void start(Class<? extends Activity> clz) {
        startActivity(new Intent(this, clz));
    }
}

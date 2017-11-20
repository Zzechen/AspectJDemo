package com.zzc.aspectjdemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zzc.aspectjdemo.adapter.ClickLogAdapter;
import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.databinding.ActivityClickBinding;
import com.zzc.aspectjdemo.event.ClickInterceptEvent;
import com.zzc.aspectjdemo.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${desc}
 */

public class ClickAopActivity extends AppCompatActivity {

    private ActivityClickBinding mBinding;
    private ClickLogAdapter mAdapter;
    private List<String> mLogList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_click);
        mBinding.btnClickAop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogList.add("click at " + TimeUtils.getCurTimeMills());
                mAdapter.notifyDataSetChanged();
                mLayoutManager.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
        mBinding.btnClickClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogList.clear();
                mAdapter.notifyDataSetChanged();
            }
        });
        mLogList = new ArrayList<>();
        mAdapter = new ClickLogAdapter(mLogList, this);
        mBinding.rvClickLog.setAdapter(mAdapter);
        mLayoutManager = (LinearLayoutManager) mBinding.rvClickLog.getLayoutManager();
        mLayoutManager.setStackFromEnd(true);
    }

    @Subscribe
    public void clickEvent(ClickInterceptEvent event) {
        String info = event.getInfo();
        mLogList.add(info);
        mAdapter.notifyDataSetChanged();
        mLayoutManager.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

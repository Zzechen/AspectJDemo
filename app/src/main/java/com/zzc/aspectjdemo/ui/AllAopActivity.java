package com.zzc.aspectjdemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.adapter.AllLogAdapter;
import com.zzc.aspectjdemo.annotation.CallMethod;
import com.zzc.aspectjdemo.annotation.ClickFilter;
import com.zzc.aspectjdemo.databinding.ActivityAllBinding;
import com.zzc.aspectjdemo.event.AspectjEvent;
import com.zzc.aspectjdemo.model.AopConstructorModel;
import com.zzc.aspectjdemo.model.AopFiledModel;
import com.zzc.aspectjdemo.model.InitConstructorModel;
import com.zzc.aspectjdemo.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${desc}
 */

public class AllAopActivity extends AppCompatActivity {

    private ActivityAllBinding mBinding;
    private ArrayList<String> mList;
    private AllLogAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private AopFiledModel mFiledModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mFiledModel = new AopFiledModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_all);
        mBinding.btnAllCall.setOnClickListener(new View.OnClickListener() {
            @ClickFilter
            @Override
            public void onClick(View v) {
                callMethod();
            }
        });
        mBinding.btnAllSet.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                String time = TimeUtils.getCurTimeString();
                mFiledModel.setTime(time);
                add("origin set field click:\nvalue:" + time);
            }
        });
        mBinding.btnAllGet.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                String time = mFiledModel.getTime();
                add("origin get filed click:\nvalue:" + time);
            }
        });
        mBinding.btnAllCallConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                new AopConstructorModel();
                add("origin call constructor click:\n time:" + TimeUtils.getCurTimeString());
            }
        });
        mBinding.btnAllExecConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                new AopConstructorModel();
                add("origin execution constructor click:\n time:" + TimeUtils.getCurTimeString());
            }
        });
        mBinding.btnAllInitConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                new InitConstructorModel();
                add("origin initialization constructor click:\n time:" + TimeUtils.getCurTimeString());
            }
        });
        mBinding.btnAllHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            @ClickFilter
            public void onClick(View v) {
                try {
                    int i = 5 / 0;
                }catch (ArithmeticException e){
                    add("create an exception:ArithmeticException");
                }
            }
        });
        mList = new ArrayList<>();
        mAdapter = new AllLogAdapter(mList, this);
        mBinding.rvAllLog.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mBinding.rvAllLog.setLayoutManager(mLayoutManager);
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayout.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.bg_divider_vertical));
        mBinding.rvAllLog.addItemDecoration(decor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_log, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.menu_all_clear == item.getItemId()) {
            mList.clear();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @CallMethod
    public void callMethod() {
        add("origin call click:" + TimeUtils.getCurTimeString());
    }

    @Subscribe
    public void callEvent(AspectjEvent event) {
        add(event.getInfo());
    }

    private void add(String info) {
        mList.add(info);
        mAdapter.notifyDataSetChanged();
        mLayoutManager.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

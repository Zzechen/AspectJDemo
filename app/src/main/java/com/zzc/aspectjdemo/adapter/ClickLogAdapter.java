package com.zzc.aspectjdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.databinding.ItemClickLogBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/11/18
 * @desc ${desc}
 */

public class ClickLogAdapter extends RecyclerView.Adapter<ClickLogAdapter.ClickLogViewHolder> {
    private List<String> mLogList;
    private LayoutInflater mInflater;

    public ClickLogAdapter(List<String> logList, Context context) {
        this.mLogList = logList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ClickLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_click_log, parent, false);
        ClickLogViewHolder holder = new ClickLogViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClickLogViewHolder holder, int position) {
        holder.binding.tvItemClickLog.setText(mLogList.get(position));
        holder.binding.tvItemClickLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLogList.size();
    }

    public static class ClickLogViewHolder extends RecyclerView.ViewHolder {
        private ItemClickLogBinding binding;

        public ClickLogViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

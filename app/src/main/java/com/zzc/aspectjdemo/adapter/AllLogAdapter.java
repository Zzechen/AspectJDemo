package com.zzc.aspectjdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.aspectjdemo.R;
import com.zzc.aspectjdemo.databinding.ItemAllLogBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/11/21
 * @desc ${desc}
 */

public class AllLogAdapter extends RecyclerView.Adapter<AllLogAdapter.AllLogViewHolder> {
    private List<String> mList;
    private LayoutInflater mInflater;

    public AllLogAdapter(List<String> list, Context context) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public AllLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_all_log, parent, false);
        AllLogViewHolder viewHolder = new AllLogViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllLogViewHolder holder, int position) {
        holder.mBinding.tvItemAllLog.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class AllLogViewHolder extends RecyclerView.ViewHolder {
        private final ItemAllLogBinding mBinding;

        public AllLogViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}

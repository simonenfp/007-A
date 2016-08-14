package com.simonenfp.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simonenfp.me.adapter.viewHolder.CommonViewHolder;

import java.util.List;

/**
 * Created by simonenfp on 2016/7/29.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.get(mContext,parent,mLayoutId);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }
    public abstract void convert(CommonViewHolder holder,T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}

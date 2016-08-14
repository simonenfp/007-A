package com.simonenfp.me.adapter.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/7/29.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    public CommonViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static CommonViewHolder get(Context context, ViewGroup parent,int resId){
        View viewItem = LayoutInflater.from(context).inflate(resId,parent,false);
        return new CommonViewHolder(context,viewItem);
    }

    /*
    * 通过id获取holder中的控件
    *
    * */
    public <T extends View>T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId,String string){
        TextView tv = getView(viewId);
        tv.setText(string);
        return this;
    }

}

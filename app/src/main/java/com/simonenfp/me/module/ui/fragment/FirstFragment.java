package com.simonenfp.me.module.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;
import com.simonenfp.me.adapter.CommonAdapter;
import com.simonenfp.me.adapter.viewHolder.CommonViewHolder;
import com.simonenfp.me.base.BaseFragment;
import com.simonenfp.me.module.model.CommonItem;
import com.simonenfp.me.module.model.PhotoEntity;
import com.simonenfp.me.module.presenter.FragmentFirstPresenter;
import com.simonenfp.me.module.view.FragmentFirstView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FirstFragment extends BaseFragment implements FragmentFirstView {

    @Bind(R.id.recycle_view_fragment_first)
    RecyclerView recycleView;
    @Bind(R.id.swipeRefreshLayout_fragment_first)
    SwipeRefreshLayout swipeRefreshLayout;

    private CommonAdapter<PhotoEntity> adapter;

    private ArrayList<PhotoEntity> photoEntities;

    private int startPage = 1;

    private FragmentFirstPresenter presenter = new FragmentFirstPresenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoEntities = new ArrayList<>();
//        Logger.d("FirstFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Logger.d("FirstFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        ButterKnife.bind(this, view);

        initView();

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Logger.d("FirstFragment onActivityCreated");
        presenter.getData("hdpic_funny",startPage);

    }

    private void initView() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);


        adapter = new CommonAdapter<PhotoEntity>(mContext, R.layout.item_list_first_fragment,photoEntities) {
            @Override
            public void convert(CommonViewHolder holder, PhotoEntity photoEntity) {
                holder.setText(R.id.tv_title,photoEntity.title);
            }
        };
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(adapter);
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
//        Logger.d("FirstFragment onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);

    }



    @Override
    public void updateView(List<PhotoEntity> list) {
        photoEntities.clear();
        photoEntities.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showToast(String s) {
        super.showToast(s);
    }

    @Override
    public void requestBefore() {
        super.showToast("load...");
    }

    @Override
    public void requestComplete() {
        super.showToast("complete");
    }
}

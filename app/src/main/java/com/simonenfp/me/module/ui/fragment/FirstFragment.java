package com.simonenfp.me.module.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simonenfp.me.R;
import com.simonenfp.me.adapter.CommonAdapter;
import com.simonenfp.me.adapter.viewHolder.CommonViewHolder;
import com.simonenfp.me.base.BaseFragment;
import com.simonenfp.me.module.model.CommonItem;
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

    private CommonAdapter<CommonItem> adapter;

    private ArrayList<CommonItem> commonItems;

    private FragmentFirstPresenter presenter = new FragmentFirstPresenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonItems = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);


        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new CommonAdapter<CommonItem>(mContext,R.layout.item_list_first_fragment,commonItems) {
            @Override
            public void convert(CommonViewHolder holder, CommonItem commonItem) {
                holder.setText(R.id.tv_title,commonItem.getDescription());
            }
        };
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(adapter);

        presenter.getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @Override
    public void initRecycleView(List<CommonItem> list) {
        commonItems.clear();
        commonItems.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String s) {
        super.showToast(s);
    }
}

package com.simonenfp.me.module.view;

import com.simonenfp.me.module.model.CommonItem;

import java.util.List;

/**
 * Created by simonenfp on 2016/7/29.
 */
public interface FragmentFirstView {
    void initRecycleView(List<CommonItem> list);
    void showToast(String s);
}

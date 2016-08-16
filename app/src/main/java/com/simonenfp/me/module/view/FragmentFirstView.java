package com.simonenfp.me.module.view;

import com.simonenfp.me.module.model.PhotoEntity;

import java.util.List;

/**
 * Created by simonenfp on 2016/7/29.
 */
public interface FragmentFirstView {
    void updateView(List<PhotoEntity> list);
    void showToast(String s);
    void requestBefore();
    void requestComplete();
}

package com.simonenfp.me.module.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Logger.d("SecondFragment onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Logger.d("SecondFragment onActivityCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Logger.d("SecondFragment onDestroyView");
    }
}

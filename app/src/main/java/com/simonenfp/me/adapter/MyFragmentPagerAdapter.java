package com.simonenfp.me.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by simonenfp on 2016/6/22.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab名的列表
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> stringList) {
        super(fm);
        list_fragment = fragmentList;
        list_Title = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    //display tab title
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position%list_Title.size());
    }
}

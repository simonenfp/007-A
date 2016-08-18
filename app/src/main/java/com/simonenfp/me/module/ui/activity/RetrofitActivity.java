package com.simonenfp.me.module.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.flyco.tablayout.SlidingTabLayout;
import com.simonenfp.me.R;
import com.simonenfp.me.adapter.MyFragmentPagerAdapter;
import com.simonenfp.me.base.BaseActivity;
import com.simonenfp.me.module.presenter.RetrofitActivityPresenter;
import com.simonenfp.me.module.ui.fragment.FirstFragment;
import com.simonenfp.me.module.ui.fragment.SecondFragment;
import com.simonenfp.me.module.ui.fragment.ThirdFragment;
import com.simonenfp.me.module.view.RetrofitActivityView;

import java.util.ArrayList;
import java.util.List;

public class RetrofitActivity extends BaseActivity implements RetrofitActivityView {

    private List<String> mListTabTitles;
    private List<Fragment> mListFragments;
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;
    
    private FragmentPagerAdapter mFragmentPagerAdapter;

    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;

    //new a main presenter to interact with model
    private RetrofitActivityPresenter mPresenter = new RetrofitActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mListTabTitles = new ArrayList<>();
        mListFragments = new ArrayList<>();
        initView();
        //presenter get data
        mPresenter.getTabData();
    }





    private void initView() {


        mTabLayout = (SlidingTabLayout) findViewById(R.id.tl_2);
        mViewPager = (ViewPager)findViewById(R.id.activity_viewPager);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mListFragments,mListTabTitles);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setViewPager(mViewPager);



    }

    @Override
    public void initTabLayoutTitle(List<String> list) {
        initData(list);
    }
    private void initData(List<String> list) {

        mListTabTitles.addAll(list);


        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mListFragments.add(mFirstFragment);
        mListFragments.add(mSecondFragment);
        mListFragments.add(mThirdFragment);


        mFragmentPagerAdapter.notifyDataSetChanged();
        mTabLayout.notifyDataSetChanged();
    }

}

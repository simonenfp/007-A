package com.simonenfp.me.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.widget.MsgView;
import com.simonenfp.me.R;
import com.simonenfp.me.activity.base.BaseActivity;
import com.simonenfp.me.adapter.MyFragmentPagerAdapter;
import com.simonenfp.me.fragment.FirstFragment;
import com.simonenfp.me.fragment.SecondFragment;
import com.simonenfp.me.fragment.ThirdFragment;
import com.simonenfp.me.mvp.presenter.MainActivityPresenter;
import com.simonenfp.me.mvp.view.MainActivityView;
import com.simonenfp.me.utils.StatusBarTools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainActivityView {

    private List<String> mListTabTitles;
    private List<Fragment> mListFragments;
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;
    
    private FragmentPagerAdapter mFragmentPagerAdapter;

    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;

    //new a main presenter to interact with model
    private MainActivityPresenter mPresenter = new MainActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


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

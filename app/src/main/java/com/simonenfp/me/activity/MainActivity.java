package com.simonenfp.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.simonenfp.me.R;
import com.simonenfp.me.adapter.MyFragmentPagerAdapter;
import com.simonenfp.me.fragment.FirstFragment;
import com.simonenfp.me.fragment.SecondFragment;
import com.simonenfp.me.fragment.ThirdFragment;
import com.simonenfp.me.mvp.presenter.MainActivityPresenter;
import com.simonenfp.me.mvp.view.MainActivityView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private List<String> mListTabTitles;
    private List<Fragment> mListFragments;
    private TabLayout mTabLayout;
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
        mListTabTitles = new ArrayList<>();
        mListFragments = new ArrayList<>();
        initView();
        //presenter get data
        mPresenter.getTabData();
    }

    private void initView() {
        //back button
        View view = findViewById(R.id.iv_main_top_bar_back);
        if (view != null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        mTabLayout = (TabLayout) findViewById(R.id.activity_main_tab);
        mViewPager = (ViewPager)findViewById(R.id.activity_viewPager);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mListFragments,mListTabTitles);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void initTabLayoutTitle(List<String> list) {
        initData(list);
    }
    private void initData(List<String> list) {

        mListTabTitles.addAll(list);

        //设置TabLayout的模式
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED|TabLayout.MODE_SCROLLABLE);
//        if (mListTabTitles != null && mListTabTitles.size() > 0){
//            for (String title : mListTabTitles)
//                mTabLayout.addTab(mTabLayout.newTab().setText(title));
//        }

        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mListFragments.add(mFirstFragment);
        mListFragments.add(mSecondFragment);
        mListFragments.add(mThirdFragment);


        mFragmentPagerAdapter.notifyDataSetChanged();

    }

}

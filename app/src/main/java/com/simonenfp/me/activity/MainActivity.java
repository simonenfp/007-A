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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.widget.MsgView;
import com.simonenfp.me.R;
import com.simonenfp.me.adapter.MyFragmentPagerAdapter;
import com.simonenfp.me.fragment.FirstFragment;
import com.simonenfp.me.fragment.SecondFragment;
import com.simonenfp.me.fragment.ThirdFragment;
import com.simonenfp.me.mvp.presenter.MainActivityPresenter;
import com.simonenfp.me.mvp.view.MainActivityView;
import com.simonenfp.me.utils.StatusBarTools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

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
        View viewRoot = findViewById(R.id.linear1);
        Log.d("DDDD","viewRoot:"+viewRoot.getId());





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            StatusBarTools.dealStatusBar(this,true);
            setTransparentStatusBar();
        }
        mListTabTitles = new ArrayList<>();
        mListFragments = new ArrayList<>();
        initView();
        //presenter get data
        mPresenter.getTabData();
    }

    private void setTransparentStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = mContentView.getChildAt(0);
        Log.d("DDDD","statusBarView:"+statusBarView.getId());
        //移除假的 View
        if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == getStatusBarHeight(this)) {
            mContentView.removeView(statusBarView);
        }
        View vi = mContentView.getChildAt(0);
        if (vi == null){
            Log.d("DDDD","vi == null");
        }else {
            Log.d("DDDD","vi != null"+" "+vi.getId());
        }
//        //不预留空间
//        if (mContentView.getChildAt(0) != null) {
//            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
//        }



    }
    //Get status bar height
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
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

        mTabLayout = (SlidingTabLayout) findViewById(R.id.tl_2);
        mViewPager = (ViewPager)findViewById(R.id.activity_viewPager);




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


        mListFragments.add(new FirstFragment());
        mListFragments.add(new SecondFragment());
        mListFragments.add(new ThirdFragment());

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mListFragments,mListTabTitles);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setViewPager(mViewPager);



//        mFragmentPagerAdapter.notifyDataSetChanged();

    }

}

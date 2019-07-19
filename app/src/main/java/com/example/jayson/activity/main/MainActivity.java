package com.example.jayson.activity.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jayson.R;
import com.example.jayson.adapter.MainViewPagerAdapter;
import com.example.jayson.base.activity.BaseActivity;
import com.example.jayson.fragment.one.OneFragment;
import com.example.jayson.fragment.three.ThreeFragment;
import com.example.jayson.fragment.two.TwoFragment;
import com.example.jayson.tools.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {
    private ViewPager viewPager;
    private BottomNavigationBar navigationBar;

    @Override
    public void showLoading(int loadingType) {
    }

    @Override
    public void hideLoading(int loadingType) {
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.showInfo(msg);
    }

    @Override
    public void showError() {
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public IMainView createView() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, true);
        initView();
        initViewPager();
        initBottomView();
    }

    private void initViewPager() {
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(oneFragment);
        list.add(twoFragment);
        list.add(threeFragment);
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.activity_main_vp);
        navigationBar = findViewById(R.id.activity_main_bv);
    }

    private void initBottomView() {
        navigationBar.addItem(new BottomNavigationItem(R.mipmap.tab_home_pre, "主页")
                .setInactiveIconResource(R.mipmap.tab_home_nor))
                .addItem(new BottomNavigationItem(R.mipmap.tab_mine_pre, "我的")
                        .setInactiveIconResource(R.mipmap.tab_mine_nor))
                .addItem(new BottomNavigationItem(R.mipmap.tab_preference_pre, "优惠")
                        .setInactiveIconResource(R.mipmap.tab_preference_nor))
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorAccent)
                .setBarBackgroundColor(R.color.colorPrimary)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .setFirstSelectedPosition(0)
                .initialise();
        navigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}

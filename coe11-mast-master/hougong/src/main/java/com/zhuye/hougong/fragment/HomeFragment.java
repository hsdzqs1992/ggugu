package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mikepenz.materialdrawer.Drawer;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.HomePagerAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.view.SearchActivity;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class HomeFragment extends BaseFragment {





    //private MyToolbar myToolbar;
    private ViewPager mviewpager;
    private PagerSlidingTabStrip mTabStrip;
    HomePagerAdapter homePagerAdapter;
    ImageView search;
    Drawer drawer;
    DrawerLayout mDrawerLayout;

    ImageView message;
    @Override
    protected void initView() {

       //drawer = new DrawerBuilder().withActivity(getActivity()).build();

        //drawer.setFullscreen(false);






        //Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"iconfont.ttf");
        mviewpager = rootView.findViewById(R.id.home_viewpager);
        mTabStrip=rootView.findViewById(R.id.tab_strip);
        search=rootView.findViewById(R.id.search);
        message=rootView.findViewById(R.id.message);
        mDrawerLayout=rootView.findViewById(R.id.home_drawer);

        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h8));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);
        //myToolbar = rootView.findViewById(R.id.home_toolbar);

        //initToolBar();
        homePagerAdapter = new HomePagerAdapter(getActivity().getSupportFragmentManager());
       mviewpager.setAdapter(homePagerAdapter);
//        myToolbar.tabs.setViewPager(mviewpager);
        mTabStrip.setViewPager(mviewpager);
    }

    private void initToolBar() {

        //myToolbar.homeLeftIcon.setText(R.string.huangguan);
        //myToolbar.homeLeftIcon.setTypeface(((MainActivity)getActivity()).typeface);
        //myyToolbar.homeRightIcon.setText(R.string.loudou);
       // mToolbar.homeRightIcon.setTypeface(((MainActivity)getActivity()).typeface);
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Contants.lunbo)
                .tag(getActivity())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        HomeBanner homeBanner = gson.fromJson(response.body(), HomeBanner.class);
                        Log.d("------",homeBanner.toString());
                        homePagerAdapter.setData(homeBanner);


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                      //Log.d("------",response.body());
                    }
                });

    }

    @Override
    protected void initListener() {
        super.initListener();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), SearchActivity.class));
                //drawer.openDrawer();
                //mDrawerLayout.openDrawer(GravityCompat.END);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }
}

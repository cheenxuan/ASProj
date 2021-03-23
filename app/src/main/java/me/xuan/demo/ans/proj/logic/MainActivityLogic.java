package me.xuan.demo.ans.proj.logic;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import me.xuan.demo.ans.proj.R;
import me.xuan.demo.ans.proj.common.tab.HiFragmentTabView;
import me.xuan.demo.ans.proj.common.tab.HiTabViewAdapter;
import me.xuan.demo.ans.proj.fragment.CategoryFragment;
import me.xuan.demo.ans.proj.fragment.FavoriteFragment;
import me.xuan.demo.ans.proj.fragment.HomePageFragment;
import me.xuan.demo.ans.proj.fragment.ProfileFragment;
import me.xuan.demo.ans.proj.fragment.RecommendFragment;
import me.xuan.hi.ui.tab.bottom.HiTabBottomInfo;
import me.xuan.hi.ui.tab.bottom.HiTabBottomLayout;
import me.xuan.hi.ui.tab.common.IHiTabLayout;

/**
 * Author: xuan
 * Created on 2021/3/23 15:09.
 * <p>
 * Describe:
 * 将MainActivity的一些逻辑内聚在这，让MainActivity更加清爽
 */
public class MainActivityLogic {

    private HiFragmentTabView fragmentTabView;
    private HiTabBottomLayout hiTabBottomLayout;
    private List<HiTabBottomInfo<?>> infoList;
    private ActivityProvider activityProvider;
    private int currentItemIndex;
    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";

    public MainActivityLogic(ActivityProvider provider,Bundle savedInstanceState) {
        this.activityProvider = provider;
        //fix 不保留活动导致的fragment重叠问题
        if (savedInstanceState != null) {
            currentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }
        initTabBottom();
    }

    public HiFragmentTabView getFragmentTabView() {
        return fragmentTabView;
    }

    public HiTabBottomLayout getHiTabBottomLayout() {
        return hiTabBottomLayout;
    }

    public List<HiTabBottomInfo<?>> getInfoList() {
        return infoList;
    }

    private void initTabBottom() {

        hiTabBottomLayout = activityProvider.findViewById(R.id.tab_bottom_layout);
        hiTabBottomLayout.setTabAlpha(0.85f);
        infoList = new ArrayList<>();
        int defaultColor = activityProvider.getResources().getColor(R.color.tabbottomDefaultColor);
        int tintColor = activityProvider.getResources().getColor(R.color.tabbottomTintColor);

        HiTabBottomInfo infoHome = new HiTabBottomInfo(
                "首页",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        infoHome.fragment = HomePageFragment.class;
        HiTabBottomInfo infoFavorite = new HiTabBottomInfo(
                "收藏",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_favorite),
                null,
                defaultColor,
                tintColor
        );
        infoFavorite.fragment = FavoriteFragment.class;
        HiTabBottomInfo infoCategory = new HiTabBottomInfo(
            "分类",
            "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_category),
            null,
                defaultColor,
                tintColor
        );
        infoCategory.fragment = CategoryFragment.class;
        HiTabBottomInfo infoRecommed=new  HiTabBottomInfo(
                "推荐",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_recommend),
                null,
                defaultColor,
                tintColor
        );
        infoRecommed.fragment = RecommendFragment.class;
        HiTabBottomInfo infoProfile= new HiTabBottomInfo(
                "我的",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_profile),
                null,
                defaultColor,
                tintColor
        );
        infoProfile.fragment = ProfileFragment.class;
        infoList.add(infoHome);
        infoList.add(infoFavorite);
        infoList.add(infoCategory);
        infoList.add(infoRecommed);
        infoList.add(infoProfile);
        
        hiTabBottomLayout.inflateInfo(infoList);
        initFragmentTabView();
        hiTabBottomLayout.addTabSelectedChangeListener(new IHiTabLayout.OnTabSelcetedListener<HiTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HiTabBottomInfo<?> preInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                MainActivityLogic.this.currentItemIndex = index;
            }
        });
        hiTabBottomLayout.defaultSelect(infoList.get(currentItemIndex));
    }
    
    private void initFragmentTabView() {
        HiTabViewAdapter tabViewAdapter = new HiTabViewAdapter(activityProvider.getSupportFragmentManager(), infoList);
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setAdapter(tabViewAdapter);
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID,currentItemIndex);
    }

    public interface ActivityProvider{

        <T extends View> T findViewById(@IdRes int idx);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);
    }
}

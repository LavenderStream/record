package org.record.tiny.demo.ui.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.ui.adapter.StoryViewPagerAdapter;

import java.util.List;

@SuppressWarnings("All")
public class StoryViewPagerWrapper implements ViewPager.OnPageChangeListener {

    public interface OnPageChangeListener {
        void onPageChange(int postion);
    }

    OnPageChangeListener mOnPageChangeListener = null;

    public void setOnPageChangeListener(OnPageChangeListener l) {
        mOnPageChangeListener = l;
    }

    private Context mContext;
    private StoryViewPagerAdapter mViewPagerAdapter;

    public StoryViewPagerWrapper(Context context, FragmentManager fragmentManager, List<Tab.DataBean> tabs, TabLayout tabLayout, ViewPager viewPager) {
        mContext = context;

        mViewPagerAdapter = new StoryViewPagerAdapter(fragmentManager, context, tabs);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

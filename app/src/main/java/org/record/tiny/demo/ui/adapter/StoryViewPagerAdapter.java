package org.record.tiny.demo.ui.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.record.tiny.demo.fragment.TabFragment;
import org.record.tiny.demo.model.Tab;

import java.util.List;

@SuppressWarnings("All")
public class StoryViewPagerAdapter extends FragmentPagerAdapter {
    private List<Tab.DataBean> mTabs;

    public StoryViewPagerAdapter(FragmentManager fm, Context context, List<Tab.DataBean> tabs) {
        super(fm);
        mTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(mTabs.get(position).getTabsName(), mTabs.get(position).getId());
    }

    @Override
    public int getCount() {
        return this.mTabs.size();
    }

    public CharSequence getPageTitle(int position) {
        return this.mTabs.get(position).getTabsName();
    }
}

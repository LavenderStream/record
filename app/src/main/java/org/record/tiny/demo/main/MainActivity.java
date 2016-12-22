package org.record.tiny.demo.main;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.google.common.collect.Lists;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.ui.view.StoryViewPagerWrapper;

import java.util.List;

import butterknife.Bind;

@SuppressWarnings("All")
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    @Bind(R.id.tl_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.vp_fragment_layout)
    ViewPager mViewPageLayout;

    private List<Tab.DataBean> mTabList = Lists.newArrayList();
    private StoryViewPagerWrapper mViewPagerWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mvpPresenter.checkPermissionsAndRun(this);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @UiThread
    @Override
    public void getTabs(List<Tab.DataBean> tabs) {
        mTabList.clear();
        mTabList.addAll(tabs);

        mViewPagerWrapper = new StoryViewPagerWrapper(this, getSupportFragmentManager(), mTabList,
                mTabLayout, mViewPageLayout);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void error(int error) {
    }
}

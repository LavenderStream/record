package org.record.tiny.demo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.record.tiny.R;
import org.record.tiny.base.BasePresenter;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.ui.view.StoryViewPagerWrapper;

import java.util.List;

import butterknife.Bind;

public class ChoiceFragment extends SimpleFragment {
    @Bind(R.id.tl_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.vp_fragment_layout)
    ViewPager mViewPageLayout;

    private List<Tab.DataBean> mTabList = Lists.newArrayList();

    public ChoiceFragment() {
    }

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String tabLists = getArguments().getString("tab_list");
            if (!TextUtils.isEmpty(tabLists)) {
                Gson gson = new Gson();
                List<Tab.DataBean> tabs = gson.fromJson(tabLists, new TypeToken<List<Tab.DataBean>>() {
                }.getType());
                mTabList.addAll(tabs);
            }
        }
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_choice;
    }

    @Override
    public void onCreateView() {
        super.onCreateView();

        new StoryViewPagerWrapper(getContext(), getActivity().getSupportFragmentManager(), mTabList,
                mTabLayout, mViewPageLayout);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

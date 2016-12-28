package org.record.tiny.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.common.collect.Lists;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.SwipeRefreshLayout;
import org.record.tiny.demo.model.StoryHeaderItem;
import org.record.tiny.demo.model.StoryItem;
import org.record.tiny.demo.ui.view.StoryRecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;

@SuppressWarnings("All")
public class TabFragment extends SimpleFragment<TabPresenter> implements TabView, StoryRecyclerViewWrapper.OnScrollListener {

    @Bind(R.id.adl_recyclerview_layout)
    RecyclerView mRecyclerViewLayout;
    @Bind(R.id.srfl_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private StoryRecyclerViewWrapper mRecyclerViewWrapper;

    private List<StoryItem> mStoryItems = Lists.newArrayList();
    private List<StoryHeaderItem> mStoryHeaderItems = Lists.newArrayList();

    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "id";

    private String name;
    private String id;

    // 提供分页加载的页数
    private int mPaging = StoryRecyclerViewWrapper.FIRST_PAGE;
    private boolean isLoadingMore = true;

    public TabFragment() {
    }

    public static TabFragment newInstance(String name, String id) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            id = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected TabPresenter createPresenter() {
        return new TabPresenter(this);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();

        mPaging = StoryRecyclerViewWrapper.FIRST_PAGE;

        mRecyclerViewWrapper = new StoryRecyclerViewWrapper(getContext(), mSwipeRefreshLayout, mRecyclerViewLayout, mStoryItems, mStoryHeaderItems);
        mRecyclerViewWrapper.setOnScrollListening(this);
        mRecyclerViewWrapper.setRefresh(true);
        mStoryItems.clear();
        mRecyclerViewWrapper.notifyDataSetChanged();

        mvpPresenter.getTabDatas(StoryRecyclerViewWrapper.FIRST_PAGE, this.id);
    }

    @Override
    public void getDatas(List<StoryItem> storys) {
        // 当刷新或者首次加载时清除数据
        if (mPaging == StoryRecyclerViewWrapper.FIRST_PAGE)
            mStoryItems.clear();

        mStoryItems.addAll(storys);

        mRecyclerViewWrapper.notifyDataSetChanged();
        mRecyclerViewWrapper.removeLoadMoreView();

        isLoadingMore = true;
    }

    @Override
    public void getHeaders(List<StoryHeaderItem> headers) {
        mStoryHeaderItems.clear();
        mStoryHeaderItems.addAll(headers);

        mRecyclerViewWrapper.headerNotifyDataSetChanged(headers);
        mRecyclerViewWrapper.setRefresh(false);
    }

    @Override
    public void error(int error) {
        mRecyclerViewWrapper.setRefresh(false);
        mRecyclerViewWrapper.notifyDataSetChanged();

        // TODO: 12/21/2016 判断错误原因
        isLoadingMore = true;
        mPaging--;
    }

    @Override
    public void onScroll() {
        if (isLoadingMore) {
            mRecyclerViewWrapper.addLoadMoreView();
            mPaging++;
            mvpPresenter.getTabDatas(mPaging, this.id);
            isLoadingMore = false;
        }
    }

    @Override
    public void onRefresh() {

        mStoryHeaderItems.clear();
        mPaging = StoryRecyclerViewWrapper.FIRST_PAGE;
        mvpPresenter.getTabDatas(StoryRecyclerViewWrapper.FIRST_PAGE, this.id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRecyclerViewWrapper != null) {
            mRecyclerViewWrapper.release();
        }
        mStoryHeaderItems.clear();
    }
}

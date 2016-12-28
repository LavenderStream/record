package org.record.tiny.demo.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.record.tiny.component.LinearLayoutManagerCatch;
import org.record.tiny.component.SwipeRefreshLayout;
import org.record.tiny.demo.model.StoryHeaderItem;
import org.record.tiny.demo.model.StoryItem;
import org.record.tiny.demo.ui.adapter.StoryRecyclerViewAdapter;
import org.record.tiny.utils.DisplayUtil;

import java.util.List;

@SuppressWarnings("All")
public class StoryRecyclerViewWrapper {

    public static final int FIRST_PAGE = 1;
    private boolean mIsRefreshing = false;

    public interface OnScrollListener {
        /**
         * 上拉刷新
         */
        void onScroll();

        /**
         * 下拉刷新
         */
        void onRefresh();
    }

    OnScrollListener mOnScrollListener = null;

    public void setOnScrollListening(OnScrollListener l) {
        mOnScrollListener = l;
    }

    private Context mContext;
    // recyclerview包装类
    private HeaderAndFooterWrapper mAdapter;
    // recyclerview 布局管理器
    private LinearLayoutManagerCatch mLinearLayoutManager;
    // google 下拉刷新组件
    private SwipeRefreshLayout mSwipeRefreshLayout;
    // ad view
    private ConvenientBanner mConvenientBanner;
    private RecyclerView mRecyclerView;
    // footer 显示加载更多
    private Button mFooterView;

    public StoryRecyclerViewWrapper(final Context context, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, List<StoryItem> datas, List<StoryHeaderItem> headers) {
        mSwipeRefreshLayout = swipeRefreshLayout;
        mRecyclerView = recyclerView;
        mContext = context;

        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManagerCatch(context));

        mAdapter = new HeaderAndFooterWrapper(new StoryRecyclerViewAdapter(context, datas));
        mRecyclerView.setAdapter(mAdapter);
        mFooterView = new Button(mContext);
        mFooterView.setVisibility(View.GONE);
        mAdapter.addFootView(mFooterView);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    if (mOnScrollListener != null)
                        mOnScrollListener.onScroll();
                }

         /*int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);*/
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        // 处理加载时禁止点击，防止recyclerview数据清除之后过快的
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mIsRefreshing) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mOnScrollListener != null)
                    mOnScrollListener.onRefresh();
            }
        });

        mConvenientBanner = new ConvenientBanner(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtil.dip2px(180));
        mConvenientBanner.setLayoutParams(params);
        mAdapter.addHeaderView(mConvenientBanner);
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void setRefresh(boolean isRefresh) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(isRefresh);
        }
    }

    public void headerNotifyDataSetChanged(List<StoryHeaderItem> headers) {
        mConvenientBanner.setPages(
                new CBViewHolderCreator<StoryHeaderHolder>() {
                    @Override
                    public StoryHeaderHolder createHolder() {
                        return new StoryHeaderHolder();
                    }
                }, headers);
        mConvenientBanner.getViewPager().getAdapter().notifyDataSetChanged();
    }

    public void release() {
        mConvenientBanner.stopTurning();
        mConvenientBanner = null;
    }

    public void removeLoadMoreView() {
        if (mFooterView != null) {
            mFooterView.setVisibility(View.GONE);
        }
    }

    public void addLoadMoreView() {
        if (mAdapter != null && mFooterView != null) {
            mFooterView.setVisibility(View.VISIBLE);
        }
    }

    public void setRecyclerViewTouching(boolean isRefreshing) {
        this.mIsRefreshing = isRefreshing;
    }
}
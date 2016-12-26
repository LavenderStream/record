package org.record.tiny.demo.favorite;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.google.common.collect.Lists;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.SimpleRecyclerAdapter;
import org.record.tiny.component.SimpleRecyclerViewHolder;
import org.record.tiny.demo.model.Favorite;
import org.record.tiny.demo.ui.view.StoryRecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;

@SuppressWarnings("All")
public class FavoriteFragment extends SimpleFragment<FavoritePresenter> implements FavoriteContract.View {

    @Bind(R.id.rv_favorite_layout)
    RecyclerView mRecyclerViewLayout;

    private SimpleRecyclerAdapter<Favorite> mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Favorite> mFavorites = Lists.newArrayList();
    // 提供分页加载的页数
    private int mPaging = StoryRecyclerViewWrapper.FIRST_PAGE;
    private boolean isLoadingMore = true;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected FavoritePresenter createPresenter() {
        return new FavoritePresenter(this);
    }

    @Override
    public void onCreateView() {
        mPaging = StoryRecyclerViewWrapper.FIRST_PAGE;

        mRecyclerViewLayout.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerViewLayout.setAdapter(mAdapter = new SimpleRecyclerAdapter<Favorite>(getContext(), mFavorites) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.favorite_item;
            }

            @Override
            public void bindData(SimpleRecyclerViewHolder holder, int position, Favorite item) {
                holder.getTextView(R.id.tv_title).setText(item.getTilte());

                Glide.with(FavoriteFragment.this).load(item.getImage().trim()).into(holder.getImageView(R.id.iv_imageview));

            }
        });

        mRecyclerViewLayout.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    if (isLoadingMore) {
                        mPaging++;
                        LogUtils.d("FavoriteFragment -> onScrolled: " + mPaging);
                        mvpPresenter.start(mPaging);

                        isLoadingMore = false;
                    }
                }
            }
        });

        mFavorites.clear();
        mAdapter.notifyDataSetChanged();

        mvpPresenter.start(mPaging);
    }

    @Override
    public void error(int error) {
        // TODO: 12/21/2016 判断错误原因
        isLoadingMore = true;
        mPaging--;
    }


    @Override
    public void loading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getFavorites(List<Favorite> favorites) {
        mFavorites.addAll(favorites);
        mAdapter.notifyDataSetChanged();

        isLoadingMore = true;
    }
}

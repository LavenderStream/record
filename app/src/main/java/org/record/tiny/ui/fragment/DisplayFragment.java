package org.record.tiny.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.common.collect.Lists;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.adapter.DisplayRecyclerAdapter;
import org.record.tiny.ui.model.Article;
import org.record.tiny.utils.EventIntent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class DisplayFragment extends SimpleFragment<DisplayPresenter> implements DisplayView, DisplayRecyclerAdapter.OnItemListener {

    @Bind(R.id.xrv_display_layout)
    XRecyclerView mRecyclerView;
    @Bind(R.id.bt_edit)
    View mEditButton;
    private View rootView;
    private List<Article> mArticles = Lists.newArrayList();
    private DisplayRecyclerAdapter mDisplayRecyclerAdapter;

    public DisplayFragment() {
    }

    public static DisplayFragment newInstance() {
        DisplayFragment fragment = new DisplayFragment();
        return fragment;
    }

    @Override
    protected DisplayPresenter createPresenter() {
        return new DisplayPresenter(this);
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_display;
    }

    @Override
    protected void onCreate() {
        initView();
        mvpPresenter.start();
    }

    @OnClick(R.id.bt_edit)
    void goEdit() {
        mActivity.addFragment(R.id.activity_main_layout, EditFragment.newInstance(), true);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setAdapter(mDisplayRecyclerAdapter = new DisplayRecyclerAdapter(getContext(), mArticles));
        mDisplayRecyclerAdapter.setOnItemListening(this);
    }

    @Override
    public void getDatas(List<Article> articles) {
        mArticles.addAll(articles);
        mDisplayRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int postion) {
        mActivity.addFragment(R.id.activity_main_layout, EditFragment.newInstance(), true);
        EventIntent.getInstance().put("intent_article", mArticles.get(postion)).send();
    }

    @Override
    public void error(int error) {
    }


}

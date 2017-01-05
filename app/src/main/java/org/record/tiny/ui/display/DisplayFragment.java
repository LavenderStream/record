package org.record.tiny.ui.display;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.common.collect.Lists;

import org.record.tiny.R;
import org.record.tiny.base.MvpFragment;
import org.record.tiny.component.adapter.DisplayRecyclerAdapter;
import org.record.tiny.databinding.FragmentDisplayBinding;
import org.record.tiny.ui.edit.EditFragment;
import org.record.tiny.ui.model.Article;
import org.record.tiny.utils.EventIntent;

import java.util.List;

@SuppressWarnings("All")
public class DisplayFragment extends MvpFragment<FragmentDisplayBinding, DisplayPresenter> implements DisplayView, DisplayRecyclerAdapter.OnItemListener {

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
    public void onCreateView() {
        super.onCreateView();
        initView();
    }

    @Override
    public void onCreate() {
        binding.setControl(this);
        mvpPresenter.start();
    }

    public void goEdit(View view) {
        mActivity.addFragment(R.id.activity_main_layout, EditFragment.newInstance(), true);
    }

    private void initView() {
        binding.xrvDisplayLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.xrvDisplayLayout.setLoadingMoreEnabled(false);
        binding.xrvDisplayLayout.setPullRefreshEnabled(false);
        binding.xrvDisplayLayout.setAdapter(mDisplayRecyclerAdapter = new DisplayRecyclerAdapter(getActivity(), mArticles));
        mDisplayRecyclerAdapter.setOnItemListening(this);
    }

    @Override
    public void getDatas(List<Article> articles) {
        mArticles.clear();
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

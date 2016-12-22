package org.record.tiny.ui.fragment;

import android.view.View;

import com.google.common.collect.Lists;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.ArticleDisplayList;
import org.record.tiny.component.adapter.DisplayRecyclerAdapter;
import org.record.tiny.component.theme.ChangeTheme;
import org.record.tiny.component.theme.ThemeRelativeLayout;
import org.record.tiny.ui.model.Article;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class DisplayFragment extends SimpleFragment<DisplayPresenter> implements DisplayView {

    @Bind(R.id.xrv_display_layout)
    ArticleDisplayList mRecyclerView;
    @Bind(R.id.bt_edit)
    View mEditButton;
    @Bind(R.id.bt_theme)
    View mThemeButton;
    @Bind(R.id.trl_display_layout)
    ThemeRelativeLayout mDisplayLayout;
    private View rootView;

    private List<Article> mArticles = Lists.newArrayList();
    private DisplayRecyclerAdapter mDisplayRecyclerAdapter;
    private boolean isDayTheme = true;

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
        mRecyclerView.setAdapter(mDisplayRecyclerAdapter = new DisplayRecyclerAdapter(getActivity(), mArticles));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mvpPresenter.start();
    }

    @OnClick({R.id.bt_edit, R.id.bt_theme})
    void goEdit(View v) {
        switch (v.getId()) {
            case R.id.bt_edit:
                mActivity.addFragment(R.id.activity_main_layout, EditFragment.newInstance(), true);
                break;
            case R.id.bt_theme:
                ChangeTheme.Model themeModel = isDayTheme ? ChangeTheme.Model.NIGHT : ChangeTheme.Model.DAY;
                isDayTheme = !isDayTheme;
                for (int i = 1; i < mArticles.size() + 1; i++) {
                    View view = mRecyclerView.getLayoutManager().findViewByPosition(i);
                    if (view instanceof ChangeTheme) {
                        ((ChangeTheme) view).themeChanged(themeModel);
                    }
                }
                mDisplayLayout.themeChanged(themeModel);
                break;
        }
    }

    @Override
    public void getDatas(List<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
        mDisplayRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(int error) {
    }
}

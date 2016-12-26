package org.record.tiny.demo.follow;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.demo.ui.view.FollowViewWrapper;
import org.record.tiny.library.AdvertView;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class FollowActivity extends MvpActivity<FollowPresenter> implements FollowContract.View {

    @Bind(R.id.rl_follow_layout)
    RelativeLayout mFollowButton;
    @Bind(R.id.tv_follow_text)
    TextView mFollowTextView;
    @Bind(R.id.adv_web_view)
    AdvertView mWebView;

    private FollowViewWrapper mFollowViewWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        mFollowViewWrapper = new FollowViewWrapper(this, mFollowButton);
        mvpPresenter.start();
    }

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public void addCollection() {
        mFollowTextView.setText(getString(R.string.story_favorite));
    }

    @Override
    public void removeCollection() {
        mFollowTextView.setText("未关注");
    }

    @Override
    public void getWeb(String web) {
        mWebView.load(web);
    }

    @Override
    public void getCollectionState(boolean isCollection) {
        mFollowTextView.setText(isCollection ? getString(R.string.story_favorite) : "未关注");
    }

    @OnClick(R.id.rl_follow_layout)
    void goFollow() {
        mFollowViewWrapper.showMenuBar();
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
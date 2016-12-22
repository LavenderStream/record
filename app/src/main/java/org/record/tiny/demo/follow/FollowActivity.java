package org.record.tiny.demo.follow;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.library.AdvertView;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class FollowActivity extends MvpActivity<FollowPresenter> implements FollowView {

    @Bind(R.id.rl_follow_layout)
    RelativeLayout mFollowButton;
    @Bind(R.id.tv_follow_text)
    TextView mFollowTextView;
    @Bind(R.id.adv_web_view)
    AdvertView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        mvpPresenter.start();
    }

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public void getTitle(String title) {
    }

    @Override
    public void getFollow(boolean isFollow) {
    }

    @Override
    public void getWeb(String web) {
        mWebView.load(web);
    }

    @OnClick(R.id.rl_follow_layout)
    void goFollow() {
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
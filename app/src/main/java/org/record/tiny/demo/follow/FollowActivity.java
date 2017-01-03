package org.record.tiny.demo.follow;

import android.os.Bundle;
import android.view.View;
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
    @Bind(R.id.ll_fragment_layout)
    View mFragmentLayout;

    private FollowViewWrapper mFollowViewWrapper;
    private boolean isCollection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        mFollowViewWrapper = new FollowViewWrapper(this, mFollowButton, mFollowTextView);
        mvpPresenter.start();
    }

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public void addCollection() {
        mFollowViewWrapper.addFollow();
    }

    @Override
    public void removeCollection() {
        mFollowViewWrapper.cancelFollow();
    }

    @Override
    public void initCollection(boolean isColl, String str) {
        mFollowViewWrapper.setCollection(!isColl);
        mFollowTextView.setText(str);
    }

    @Override
    public void getWeb(String web) {
        mWebView.load(web);
    }

    @OnClick(R.id.rl_follow_layout)
    void goFollow() {
        if (!mFollowViewWrapper.isCollection()) {
            mvpPresenter.remove();
        } else {
            mvpPresenter.add();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
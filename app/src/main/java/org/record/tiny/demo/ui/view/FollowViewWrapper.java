package org.record.tiny.demo.ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.library.MenuBar;

@SuppressWarnings("All")
public class FollowViewWrapper implements MenuBar.OnClickListener {

    private boolean isCollection = false;

    public interface OnClickListener {
        void add();

        void jump();
    }

    OnClickListener mOnClickListener = null;

    public void setOnClickListering(OnClickListener l) {
        mOnClickListener = l;
    }

    private MenuBar mMenuBar;
    private View mFlagView;
    private TextView mFollowTextView;
    private Context mContext;

    public FollowViewWrapper(Activity activity, View buttonMenu, TextView followView) {
        mContext = activity;
        mMenuBar = new MenuBar(activity);
        mMenuBar.setOnClickListering(this);
        mFlagView = buttonMenu;
        mFollowTextView = followView;
    }

    public void showMenuBar() {
        if (mMenuBar != null && mFlagView != null) {
            mMenuBar.showPopupWindow(mFlagView);
        }
    }

    public void hideMenuBar() {
        if (mMenuBar != null && mFlagView != null) {
            mMenuBar.dismiss();
        }
    }

    public void addFollow() {
        isCollection = false;
        mFollowTextView.setText("取消收藏");
    }

    public void cancelFollow() {
        isCollection = true;
        mFollowTextView.setText(mContext.getString(R.string.story_favorite));
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public boolean isCollection() {
        return isCollection;
    }

    @Override
    public void add() {
        mOnClickListener.add();
    }

    @Override
    public void jump() {
        mOnClickListener.jump();
    }

    public void setText(String text) {
        mMenuBar.setLeftButtonText(mFollowTextView.getText().toString());
    }
}

package org.record.tiny.demo.ui.view;

import android.app.Activity;
import android.view.View;

import org.record.tiny.library.MenuBar;

@SuppressWarnings("All")
public class FollowViewWrapper {
    private MenuBar mMenuBar;
    private View mFlagView;

    public FollowViewWrapper(Activity activity, View buttonMenu) {
        mMenuBar = new MenuBar(activity);
        mFlagView = buttonMenu;
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
}

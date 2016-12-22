package org.record.tiny.component;

import android.content.Context;
import android.util.AttributeSet;

@SuppressWarnings("All")
public class SwipeRefreshLayout extends android.support.v4.widget.SwipeRefreshLayout {
    private boolean mMeasured = false;
    private boolean mRefresh = false;

    public SwipeRefreshLayout(Context context) {
        super(context);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mRefresh);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mRefresh = refreshing;
        }
    }
}

package org.record.tiny.component;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.record.tiny.component.theme.ChangeTheme;

@SuppressWarnings("All")
public class ArticleDisplayList extends XRecyclerView implements ChangeTheme {

    private LinearLayoutManager mLinearLayoutManager;

    public ArticleDisplayList(Context context) {
        this(context, null);
    }

    public ArticleDisplayList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArticleDisplayList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(context));
        this.setLoadingMoreEnabled(false);
        this.setPullRefreshEnabled(false);
    }

    public LinearLayoutManager getLayoutManager() {
        return mLinearLayoutManager;
    }

    @Override
    public void themeChanged(Model themeMode) {
    }
}

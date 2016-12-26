package org.record.tiny.demo.favorite;

import android.os.Bundle;
import android.widget.RelativeLayout;

import org.record.tiny.R;
import org.record.tiny.base.BasePresenter;
import org.record.tiny.base.MvpActivity;

import butterknife.Bind;

public class FavoriteActivity extends MvpActivity<BasePresenter> {

    @Bind(R.id.rl_fragment_layout)
    RelativeLayout mFragmentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        addFragment(R.id.rl_fragment_layout, FavoriteFragment.newInstance());
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}

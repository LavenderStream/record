package org.record.tiny.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import org.record.tiny.utils.DisplayUtil;


@SuppressWarnings("All")
public abstract class MvpActivity<B extends ViewDataBinding, P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;
    protected B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayUtil.myStatusBar(this);
        if (createLayoutId() != -1)
            binding = DataBindingUtil.setContentView(this, createLayoutId());
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    protected abstract int createLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}

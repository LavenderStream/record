package org.record.tiny.demo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import org.record.tiny.base.BaseActivity;
import org.record.tiny.base.BasePresenter;

/**
 * Created by tiny on 04/01/2017.
 */
public abstract class BindingActivity<B extends ViewDataBinding,  P extends BasePresenter>  extends BaseActivity {
    protected P mvpPresenter;
    protected B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

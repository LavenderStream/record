package org.tiny.lib.core;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tiny on 4/21/2018
 */

public abstract class BaseActivity<P extends BasePresenter,
        B extends ViewDataBinding> extends
        AppCompatActivity implements BaseView {
    protected P mPresenter;
    protected B mBinding;
}

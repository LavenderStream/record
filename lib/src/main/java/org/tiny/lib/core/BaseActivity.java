package org.tiny.lib.core;

/**
 * Created by tiny on 4/21/2018
 */

public abstract class BaseActivity<P extends BasePresenter> extends
        UiActivity implements BaseView {
    public P mPresenter;
}

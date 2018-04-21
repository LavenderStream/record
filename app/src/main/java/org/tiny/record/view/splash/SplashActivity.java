package org.tiny.record.view.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.fork.annotations.ForkLayoutId;
import org.fork.annotations.ForkPresenter;
import org.tiny.lib.core.BaseActivity;
import org.tiny.lib.core.Fork;
import org.tiny.record.R;

/**
 * Created by tiny on 4/21/2018
 */
@ForkLayoutId(R.layout.activity_splash)
@ForkPresenter(SplashPresenter.class)
public class SplashActivity extends BaseActivity<SplashPresenter> implements
        SplashContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fork.bind(this);
    }
}

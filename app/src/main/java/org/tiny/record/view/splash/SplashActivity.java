package org.tiny.record.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.fork.annotations.ForkPresenter;
import org.tiny.lib.core.BaseActivity;
import org.tiny.lib.core.Fork;
import org.tiny.record.R;
import org.tiny.record.databinding.ActivitySplashBinding;

/**
 * Created by tiny on 4/21/2018
 */
@ForkPresenter(SplashPresenter.class)
public class SplashActivity extends BaseActivity<SplashPresenter, ActivitySplashBinding> implements
        SplashContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fork.bind(this, R.layout.activity_splash);


        Intent intent = new Intent();
        intent.setAction("org.tiny.main.view.MainActivity");
        sendBroadcast(intent);
    }
}

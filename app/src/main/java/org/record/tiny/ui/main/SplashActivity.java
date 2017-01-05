package org.record.tiny.ui.main;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.record.tiny.base.MvpActivity;

@SuppressWarnings("All")
public class SplashActivity extends MvpActivity<ViewDataBinding, SplashPresenter> implements SplashView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mvpPresenter.start(this);
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        SplashActivity.this.finish();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int createLayoutId() {
        return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void error(int error) {
    }
}

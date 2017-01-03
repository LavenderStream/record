package org.record.tiny.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;

@SuppressWarnings("All")
public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void error(int error) {

    }
}

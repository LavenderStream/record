package org.record.tiny.ui.view;

import android.app.Activity;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.utils.Config;
import org.record.tiny.utils.Error;

import rx.functions.Action1;

@SuppressWarnings("All")
public class SplashPresenter extends BasePresenter<SplashView> {
    private String mLocalInfo = "";

    public SplashPresenter(SplashView context) {
        attachView(context);
    }

    public void start(Activity activity) {
        new RxPermissions(activity).request(Config.getAllPermissions()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    getData();
                    mvpView.startMainActivity();
                } else {
                    mvpView.error(Error.PERMISSION_ERROR);
                }
            }
        });
    }

    /**
     * 从网络拉取数据
     */
    public void getData() {
    }

    private void getLocalInfo() {
    }

    private void getWeatherInfo() {

    }

    private void getTimeInfo() {
    }
}

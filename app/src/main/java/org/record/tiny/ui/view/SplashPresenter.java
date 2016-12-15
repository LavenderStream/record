package org.record.tiny.ui.view;

import android.app.Activity;
import android.support.annotation.UiThread;
import android.util.Log;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.utils.Callback;
import org.record.tiny.utils.Config;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.LocationUtil;

import rx.functions.Action1;

@SuppressWarnings("All")
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final String TAG = SplashPresenter.class.getSimpleName();

    private String mLocalInfo = "";

    public SplashPresenter(SplashView context) {
        attachView(context);
    }

    public void start(Activity activity) {
        new RxPermissions(activity).request(Config.getAllPermissions()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    getData(new Callback.simpleCallBack() {
                        @Override
                        @UiThread
                        public void Done() {
                            mvpView.startMainActivity();
                        }
                    });
                } else {
                    mvpView.error(Error.PERMISSION_ERROR);
                }
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        LocationUtil.getInstance().stop();
    }

    /**
     * 从网络拉取数据
     */
    public void getData(Callback.simpleCallBack done) {
        getLocalInfo(done);
    }

    private void getLocalInfo(final Callback.simpleCallBack done) {
        LocationUtil.getInstance().start(new Callback.TCallBack<String>() {
            @Override
            public void Done(String city) {
                Log.d(TAG, "Done: " + city);
                if (done != null) {
                    done.Done();
                }
            }
        });
    }

    private void getWeatherInfo() {

    }

    private void getTimeInfo() {
    }
}

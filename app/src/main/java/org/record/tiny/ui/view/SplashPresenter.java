package org.record.tiny.ui.view;

import android.app.Activity;
import android.support.annotation.UiThread;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.ui.model.ViewModel;
import org.record.tiny.utils.Callback;
import org.record.tiny.utils.ChinaNumTrans;
import org.record.tiny.utils.Config;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.LocationUtil;
import org.record.tiny.utils.RealmUtils;
import org.record.tiny.utils.RxCountDown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

@SuppressWarnings("All")
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final String TAG = SplashPresenter.class.getSimpleName();

    private String mLocalInfo = "";
    private ViewModel mViewModel = new ViewModel();

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
                            onUnsubscribe();
                            RealmUtils.getInstance().insertObject(mViewModel);
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
        startDemons();
        getTimeInfo();
        getLocalInfo(done);
    }

    private void getLocalInfo(final Callback.simpleCallBack done) {
        LocationUtil.getInstance().start(new Callback.TCallBack<String>() {
            @Override
            public void Done(String address) {
                if (done != null) {
                    mViewModel.setAddress(address);
                    done.Done();
                }
            }
        });
    }

    private void getWeatherInfo() {

    }

    private void startDemons() {
        Observable observable = RxCountDown.countdown(4);
        Subscriber subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                LocationUtil.getInstance().stop();
                RealmUtils.getInstance().insertObject(mViewModel);
                mvpView.startMainActivity();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Integer integer) {
            }
        };

        addSubscription(observable, subscriber);
    }


    private void getTimeInfo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        try {
            Date date = formatter.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            mViewModel.setDay(ChinaNumTrans.convertNumber(day));
            mViewModel.setMonth(ChinaNumTrans.convertNumber(month));
            mViewModel.setYear(ChinaNumTrans.simpleConvertNumber(year));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

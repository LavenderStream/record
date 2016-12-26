package org.record.tiny.ui.view;

import android.app.Activity;
import android.support.annotation.UiThread;

import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.net.RxSubscriber;
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

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

@SuppressWarnings("All")
public class SplashPresenter extends BasePresenter<SplashView> {
    private static final String TAG = SplashPresenter.class.getSimpleName();

    private String mLocalInfo = "";
    private ViewModel mViewModel = new ViewModel();

    public SplashPresenter(SplashView context) {
        attachView(context);
    }

    public void start(Activity activity) {
        addSubscription(apiStores.getText(), new ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody model) {
               model.source();
                LogUtils.d("SplashPresenter -> onSuccess: ");
            }

            @Override
            public void onFailure(int errorCode) {
                LogUtils.d("SplashPresenter -> errorCode: " + errorCode);
            }
        });
        new RxPermissions(activity).request(Config.getAllPermissions()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
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
        Flowable observable = RxCountDown.countdown(4);

        addSubscription(observable, new RxSubscriber<Integer>() {
            @Override
            public void onNext(Integer o) {
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
                onUnsubscribe();
                LocationUtil.getInstance().stop();
                RealmUtils.getInstance().insertObject(mViewModel);
                mvpView.startMainActivity();
            }
        });
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

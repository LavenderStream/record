package org.record.tiny.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.record.tiny.net.ApiStores;
import org.record.tiny.net.AppClient;
import org.record.tiny.net.RxSubscriber;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@SuppressWarnings("All")
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    public BaseActivity mActivity;
    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    public <T> void addSubscription(Flowable flowable, final RxSubscriber<T> subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (subscriber == null) {
            Log.e(TAG, "rx callback is null");
            return;
        }

        Disposable disposable = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T o) throws Exception {
                        subscriber.onNext(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        subscriber.onError(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        subscriber.onComplete();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void addSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    private void onUnsubscribe() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    /**
     * 配置全局字体
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void addFragment(int containerViewId, Fragment fragment) {
        addFragment(containerViewId, fragment, false);
    }

    public void addFragment(int containerViewId, Fragment fragment, boolean addBackStack) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        if (addBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

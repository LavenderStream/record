package org.record.tiny.base;


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

@SuppressWarnings("All")
public class BasePresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();

    public V mvpView;
    protected ApiStores apiStores;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = AppClient.retrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void addSubscription(Flowable flowable, final RxSubscriber subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (subscriber == null) {
            Log.e(TAG, "Callback.Subscriber is null object");
            return;
        }

        Disposable disposable = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        subscriber.onNext(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        subscriber.onNext(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        subscriber.onComplete();
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}

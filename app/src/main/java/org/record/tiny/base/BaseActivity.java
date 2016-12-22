package org.record.tiny.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import org.record.tiny.net.ApiStores;
import org.record.tiny.net.AppClient;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@SuppressWarnings("All")
public class BaseActivity extends AppCompatActivity {
    public BaseActivity mActivity;
    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void addFragment(int containerViewId, Fragment fragment) {
        addFragment(containerViewId, fragment, false);
    }

    public void addFragment(int containerViewId, Fragment fragment, boolean addBackStack) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        if (addBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

package org.record.tiny.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected View mRootView;
    protected P mvpPresenter;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = inflater.inflate(createViewLayoutId(), null);
            unbinder = ButterKnife.bind(this, mRootView);
            mvpPresenter = createPresenter();
            onCreateView();
        }
        onCreate();
        return mRootView;
    }

    protected abstract int createViewLayoutId();

    protected abstract P createPresenter();

    public void onCreate() {
    }

    public void onCreateView() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mvpPresenter = createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}

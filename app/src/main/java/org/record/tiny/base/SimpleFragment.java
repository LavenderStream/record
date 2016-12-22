package org.record.tiny.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class SimpleFragment<P extends BasePresenter> extends BaseFragment {

    protected View mRootView;
    protected P mvpPresenter;

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
            ButterKnife.bind(this, mRootView);
            mvpPresenter = createPresenter();
            onCreateView();
        }
        onCreate();
        return mRootView;
    }

    protected abstract int createViewLayoutId();

    public void onCreateView() {
    }

    public void onCreate() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mRootView = null;
            mvpPresenter = null;
        }
    }
}


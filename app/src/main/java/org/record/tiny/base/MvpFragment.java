package org.record.tiny.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class MvpFragment<B extends ViewDataBinding, P extends BasePresenter> extends BaseFragment {

    protected View mRootView;
    protected B binding;
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
            binding = DataBindingUtil.inflate(inflater, createViewLayoutId(), container, false);
            mRootView = binding.getRoot();
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
    }
}

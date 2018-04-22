package org.tiny.lib.core;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;

import org.fork.annotations.IProvider;

/**
 * Created by tiny on 4/21/2018
 */

public class Fork {
    private static final String TAG = "Fork";

    public static void bind(BaseActivity activity, @LayoutRes int layoutId, boolean vm) {
        IProvider provider = null;
        try {
            try {
                provider = (IProvider) Class.forName(
                        activity.getClass().getName() + "$$Fork_IProvider").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (provider != null) {
            activity.mBinding = DataBindingUtil.setContentView(activity, layoutId);
            activity.mPresenter = (BasePresenter) provider.getPresenter(activity);
        }
    }

    public static void bind(BaseActivity activity, @LayoutRes int layoutId) {
        bind(activity, layoutId, true);
    }
}

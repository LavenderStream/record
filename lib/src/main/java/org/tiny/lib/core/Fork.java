package org.tiny.lib.core;

import org.fork.annotations.IProvider;

/**
 * Created by tiny on 4/21/2018
 */

public class Fork {
    private static final String TAG = "Fork";

    public static void bind(BaseActivity activity) {
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
            //activity.binding = DataBindingUtil.setContentView(activity, provider.getLayoutId());
            activity.setContentView(provider.getLayoutId());
            activity.mPresenter = (BasePresenter) provider.getPresenter(activity);
        }

    }
}

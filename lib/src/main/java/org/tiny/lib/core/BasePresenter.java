package org.tiny.lib.core;

/**
 * Created by tiny on 4/21/2018.
 */

public class BasePresenter<V extends BaseView> {
    private V mView;

    public BasePresenter(V view) {
        mView = view;
    }
}

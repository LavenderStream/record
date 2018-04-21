package org.tiny.main.view;

import org.tiny.lib.core.BasePresenter;

/**
 * Created by tiny on 4/21/2018
 */

public class MainPresenter extends BasePresenter<MainContract.IView> {
    public MainPresenter(MainContract.IView view) {
        super(view);
    }
}

package org.tiny.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.tiny.lib.core.BaseActivity;
import org.tiny.lib.core.Fork;

/**
 * Created by tiny on 4/21/2018
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fork.bind(this);
    }
}

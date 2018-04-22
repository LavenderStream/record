package org.tiny.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.fork.annotations.ForkPresenter;
import org.tiny.componentmain.R;
import org.tiny.componentmain.databinding.MainActivityMainBinding;
import org.tiny.lib.core.BaseActivity;
import org.tiny.lib.core.Fork;

/**
 * Created by tiny on 4/21/2018
 */
@ForkPresenter(MainPresenter.class)
@Route(path = "/test/activity")
public class MainActivity extends BaseActivity<MainPresenter, MainActivityMainBinding> implements
        MainContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fork.bind(this, R.layout.main_activity_main);

        mBinding.rootView.setBackgroundColor(Color.RED);
    }
}

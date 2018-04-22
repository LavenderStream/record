package org.tiny.main.view;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by tiny on 4/22/2018.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}

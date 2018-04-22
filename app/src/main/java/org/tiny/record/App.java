package org.tiny.record;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * Created by tiny on 4/21/2018
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }
}

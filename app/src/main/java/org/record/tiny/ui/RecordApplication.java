package org.record.tiny.ui;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import org.record.tiny.demo.model.User;
import org.record.tiny.utils.Config;

@SuppressWarnings("All")
public class RecordApplication extends Application {

    private static User sUser;
    protected static Context sContext;

    private static RecordApplication single = null;

    public static RecordApplication getInstance() {
        if (single == null) {
            single = new RecordApplication();
        }
        return single;
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        Config.configDefaultFont();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public User getUser() {
        if (sUser == null) {
            sUser = new User();
        }
        return sUser;
    }

    public void setUser(User sUser) {
        this.sUser = sUser;
    }
}

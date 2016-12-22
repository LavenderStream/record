package org.record.tiny.ui;

import android.app.Application;
import android.content.Context;

import org.record.tiny.demo.model.User;
import org.record.tiny.utils.Config;

@SuppressWarnings("All")
public class RecordApplication extends Application {

    public static User sUser = new User();
    protected static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        Config.configDefaultFont();
    }

    public User getsUser() {
        return sUser;
    }

    public void setsUser(User sUser) {
        this.sUser = sUser;
    }
}

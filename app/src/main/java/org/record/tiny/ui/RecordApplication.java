package org.record.tiny.ui;

import android.app.Application;
import android.content.Context;

import org.record.tiny.utils.Config;

@SuppressWarnings("All")
public class RecordApplication extends Application {
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
}

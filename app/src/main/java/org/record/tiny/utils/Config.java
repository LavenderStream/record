package org.record.tiny.utils;

import android.Manifest;

import org.record.tiny.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressWarnings("All")
public class Config {
    public static final int DELAYED_TIME = 1800;
    public static final String DEFAULT_FONT_FILE = "font.ttf";

    public static void configDefaultFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONT_FILE)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    /**
     * @return 应用所有需要的权限
     */
    public static String[] getAllPermissions() {
        return new String[]{Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }
}

package org.record.tiny.utils;

import android.util.DisplayMetrics;

import org.record.tiny.ui.RecordApplication;

@SuppressWarnings("All")
public class DisplayUtil {

    public static int getScreenWidth() {
        DisplayMetrics dm = RecordApplication.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getSceenHeight() {
        DisplayMetrics dm = RecordApplication.getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int dip2px(float dipValue) {
        final float scale = RecordApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = RecordApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = RecordApplication.getContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusBarHeight;
    }
}

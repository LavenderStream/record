package org.record.tiny.utils;


public class BitmapUtil {
    private static BitmapUtil ourInstance = new BitmapUtil();

    private BitmapUtil() {
    }

    public static BitmapUtil getInstance() {
        return ourInstance;
    }
}

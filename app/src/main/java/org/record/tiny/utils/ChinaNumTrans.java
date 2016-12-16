package org.record.tiny.utils;

import android.text.TextUtils;

import java.util.Scanner;

@SuppressWarnings("All")
public class ChinaNumTrans {
    private static Scanner sc;

    private static String input;

    private static String units[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿"};

    private static String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    private static String result[];

    public static String simpleConvertNumber(int in) {
        String result = "";
        String[] strArray = String.valueOf(in).trim().split("");
        for (String s : strArray) {
            if (!TextUtils.isEmpty(s))
                result += nums[Integer.valueOf(s)];
        }
        return result;
    }

    public static String convertNumber(int in) {
        String input = in + "";
        String out = "";
        result = new String[input.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = String.valueOf(input.charAt(i));
        }
        int back = 0;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals("0")) {
                back = result.length - i - 1;
                out += nums[Integer.parseInt(result[i])];
                out += units[back];
            } else {
                if (i == result.length - 1) {

                } else {
                    if (!result[i + 1].equals("0")) {
                        out += nums[0];
                    }
                }
            }
        }
        return out;
    }
}

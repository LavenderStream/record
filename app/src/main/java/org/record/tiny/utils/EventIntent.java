package org.record.tiny.utils;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * 使用eventbus 进行页面间的数值传递
 */
@SuppressWarnings("All")
public class EventIntent {
    private static StickEvent stickEvent = new StickEvent();

    private static EventIntent ourInstance = new EventIntent();

    private EventIntent() {
    }

    public static EventIntent getInstance() {
        return ourInstance;
    }

    /**
     * 得到指定key的数据
     * 数据成功返回后会清楚当前查看的数据
     *
     * @param key
     * @return
     */
    public static Object get(@NonNull String key) {
        StickEvent stick = EventBus.getDefault().getStickyEvent(StickEvent.class);
        if (stick == null) {
            LogUtils.e("stick is null");
            return null;
        }

        Map<String, Object> map = stick.getStickMap();
        if (map == null) {
            LogUtils.e("map is null");
            return null;
        }

        Object obj = map.get(key);
        stick.getStickMap().remove(key);

        EventBus.getDefault().postSticky(stick);
        return obj;
    }

    public void clear() {
        stickEvent.getStickMap().clear();
        EventBus.getDefault().removeAllStickyEvents();
    }

    public EventIntent put(String key, Object value) {
        stickEvent.getStickMap().put(key, value);
        return this;
    }

    public void send() {
        EventBus.getDefault().postSticky(stickEvent);
    }

    public Object get(@NonNull String key, @NonNull Object t) {
        StickEvent stick = EventBus.getDefault().getStickyEvent(StickEvent.class);
        if (stick == null) {
            LogUtils.e("stick is null");
        }

        Map<String, Object> map = stick.getStickMap();
        if (map == null) {
            LogUtils.e("map is null");
        }

        Object obj = map.get(key);
        stick.getStickMap().remove(key);

        EventBus.getDefault().postSticky(stick);

        if (t instanceof Number) {

        }
        return obj;
    }

    public StickEvent get() {
        StickEvent stick = EventBus.getDefault().getStickyEvent(StickEvent.class);
        EventBus.getDefault().removeAllStickyEvents();
        return stick;
    }

    public StickEvent cat() {
        return EventBus.getDefault().getStickyEvent(StickEvent.class);
    }

    public Object cat(@NonNull String key) {
        StickEvent stick = EventBus.getDefault().getStickyEvent(StickEvent.class);
        if (stick == null) {
            return null;
        }
        return stick.getStickMap().get(key);
    }
}

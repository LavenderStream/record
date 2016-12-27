package org.record.tiny.utils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

@SuppressWarnings("All")
public class RxIntent implements Disposable {
    private static final String TAG = RxIntent.class.getSimpleName();

    private final Map<String, Object> mStickyEventMap = new HashMap<>();

    private static RxIntent instance;
    private Disposable mDisposable;

    private RxIntent() {
    }

    public static RxIntent getInstance() {
        if (instance == null) {
            instance = new RxIntent();
        }
        return instance;
    }

    @Override
    public void dispose() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return mStickyEventMap.size() == 0 ? true : false;
    }

    /**
     * 发送一个新Sticky事件
     */

    public RxIntent postEvent(String key, Object event) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(key, event);
        }
        return this;
    }

    public void send() {
        RxBus.getInstance().post(mStickyEventMap);
    }

    public Disposable subscribe(final Consumer<HashMap> consumer) {
        mDisposable = RxBus.getInstance().toFlowable(HashMap.class).subscribe(new Consumer<HashMap>() {
            @Override
            public void accept(HashMap map) throws Exception {
                HashMap<String, Object> temp = new HashMap<String, Object>();
                temp.putAll(map);
                mStickyEventMap.clear();
                consumer.accept(temp);
            }
        });
        return this;
    }
    
    public Disposable subscribe(final Consumer<HashMap> consumer, final String... keys) {
        mDisposable = RxBus.getInstance().toFlowable(HashMap.class).subscribe(new Consumer<HashMap>() {
            @Override
            public void accept(HashMap map) throws Exception {
                HashMap<String, Object> temp = new HashMap<String, Object>();
                for (String key : keys) {
                    if (map.containsKey(key)) {
                        temp.put(key, map.get(key));
                        map.remove(key);
                    }
                }
                consumer.accept(temp);
            }
        });
        return this;
    }
}

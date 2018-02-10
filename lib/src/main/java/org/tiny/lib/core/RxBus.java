package org.tiny.lib.core;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by tiny on 2/10/2018
 */

public class RxBus
{
    private static volatile RxBus mInstance;

    private final Subject mBus;

    public RxBus()
    {
        mBus = PublishSubject.create().toSerialized();
    }

    /**
     * 单例模式RxBus
     *
     * @return
     */
    public static RxBus getInstance()
    {
        RxBus rxBus2 = mInstance;
        if (mInstance == null)
        {
            synchronized (RxBus.class)
            {
                rxBus2 = mInstance;
                if (mInstance == null)
                {
                    rxBus2 = new RxBus();
                    mInstance = rxBus2;
                }
            }
        }
        return rxBus2;
    }

    /**
     * 发送消息
     *
     * @param object
     */
    public void post(Object object)
    {
        mBus.onNext(object);
    }

    /**
     * 接收消息
     *
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> type)
    {
        return mBus.toFlowable(BackpressureStrategy.BUFFER).ofType(type);
    }
}

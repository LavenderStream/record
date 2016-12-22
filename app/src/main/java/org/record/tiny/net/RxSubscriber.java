package org.record.tiny.net;


public interface RxSubscriber<T> {
    void onNext(T t);

    void onError(Throwable t);

    void onComplete();
}

package org.record.tiny.utils;

@SuppressWarnings("All")
public class Callback {

    public interface simpleCallBack {
        void Done();
    }

    public interface TCallBack<T> {
        void Done(T t);
    }
}

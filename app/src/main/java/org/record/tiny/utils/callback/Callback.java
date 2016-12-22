package org.record.tiny.utils.callback;

@SuppressWarnings("All")
public class Callback {

    public interface simpleCallBack {
        void Done();
    }

    public interface TCallBack<T> {
        void Done(T t);
    }
}

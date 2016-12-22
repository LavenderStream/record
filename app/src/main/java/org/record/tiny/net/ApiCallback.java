package org.record.tiny.net;

import com.apkfuns.logutils.LogUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.record.tiny.utils.Callback;

@SuppressWarnings("All")
public abstract class ApiCallback<M> implements Callback.Subscriber<M> {
    public static final int UNKNOWN_NET_ERROR = 0x500;

    public abstract void onSuccess(M model);

    public abstract void onFailure(int errorCode);

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtils.i("ApiCallback -> onError: " + code);
            onFailure(code);
        } else {
            onFailure(UNKNOWN_NET_ERROR);
        }
    }
}
package org.record.tiny.net;

import com.apkfuns.logutils.LogUtils;

import org.record.tiny.utils.Error;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

@SuppressWarnings("All")
public abstract class ApiCallback<M> extends Subscriber<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(int errorCode);

    public void onFinish() {}

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtils.i("ApiCallback -> onError: " + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            LogUtils.i("ApiCallback -> onError: " + msg);
            onFailure(code);
        } else {
            onFailure(Error.UNKNOWN_NET_ERROR);
        }
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}

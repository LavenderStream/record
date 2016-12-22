package org.record.tiny.demo.follow;

import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.EventIntent;

@SuppressWarnings("All")
public class FollowPresenter extends BasePresenter<FollowView> {

    public FollowPresenter(FollowView followView) {
        attachView(followView);
    }

    public void start() {
        getWebUrl();
    }

    private void getWebUrl() {
        String webUrl = (String) EventIntent.get("web_url");
        LogUtils.d("FollowPresenter -> getWebUrl: " + webUrl);
        if (TextUtils.isEmpty(webUrl))
            mvpView.error(Error.UNKNOWN_NET_ERROR);
        else
            mvpView.getWeb(webUrl);
    }

    public void follow() {

    }

    public void unFollow() {

    }
}

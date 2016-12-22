package org.record.tiny.demo.main;

import android.app.Activity;

import com.apkfuns.logutils.LogUtils;
import com.google.common.collect.Maps;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.model.UserNet;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.Config;
import org.record.tiny.utils.Error;

import java.util.Map;

import rx.functions.Action1;

@SuppressWarnings("All")
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void checkPermissionsAndRun(Activity activity) {
        new RxPermissions(activity).request(Config.getAllPermissions()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    getTabs();
                } else {
                    mvpView.error(Error.PERMISSION_ERROR);
                }
            }
        });
    }

    /**
     * 获取tab栏信息
     */
    private void getTabs() {
        addSubscription(apiStores.getTabs("top_tab"), new ApiCallback<Tab>() {
            @Override
            public void onSuccess(Tab tab) {
                mvpView.getTabs(tab.getData());
                mvpView.hideLoading();
            }

            @Override
            public void onFailure(int error) {
                mvpView.error(error);
                mvpView.hideLoading();
            }
        });
    }

    private void login() {
        mvpView.showLoading();
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("phone", "15640673218");
        paramsMap.put("password", "duxin9259");
        addSubscription(apiStores.login(paramsMap), new ApiCallback<UserNet>() {
            @Override
            public void onSuccess(UserNet model) {
                LogUtils.d("MainPresenter -> onSuccess: " + model);
                RecordApplication.sUser.setName(model.getData().getNickname());
                RecordApplication.sUser.setToken(model.getData().getAccess_token());
                getTabs();
            }

            @Override
            public void onFailure(int errorCode) {
                mvpView.error(errorCode);
                mvpView.hideLoading();
            }
        });
    }
}

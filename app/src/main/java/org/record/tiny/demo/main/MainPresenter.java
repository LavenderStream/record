package org.record.tiny.demo.main;

import android.app.Activity;

import com.google.common.collect.Maps;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.model.UserNet;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.Config;
import org.record.tiny.utils.Error;

import java.util.List;
import java.util.Map;

import rx.functions.Action1;

@SuppressWarnings("All")
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View mvpView) {
        super(mvpView);
    }

    public void start(Activity activity) {
        mvpView.showLoading();
        checkPermissionsImpl(activity);
    }

    @Override
    public void checkPermissions(boolean success) {
        if (success)
            loginImpl();
        else {
            mvpView.error(Error.PERMISSION_ERROR);
            mvpView.hideLoading();
        }
    }

    @Override
    public void login(boolean success) {
        if (success) {
            getTabsImpl();
        } else {
            getTabsImpl();
            mvpView.error(Error.LOGIN_FAIL_ERROR);
        }
    }

    @Override
    public void getTabs(boolean success, List<Tab.DataBean> tabs) {
        if (success) {
            mvpView.getTabs(tabs);
            mvpView.hideLoading();
        } else {
            mvpView.error(Error.UNKNOWN_NET_ERROR);
            mvpView.hideLoading();
        }
    }

    private void checkPermissionsImpl(Activity activity) {
        new RxPermissions(activity).request(Config.getAllPermissions()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                checkPermissions(granted);
            }
        });
    }

    private void getTabsImpl() {
        addSubscription(apiStores.getTabs("top_tab"), new ApiCallback<Tab>() {
            @Override
            public void onSuccess(Tab tab) {
                getTabs(true, tab.getData());
            }

            @Override
            public void onFailure(int error) {
                getTabs(false, null);
            }
        });
    }

    private void loginImpl() {
        mvpView.showLoading();
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("phone", "15640673218");
        paramsMap.put("password", "duxin9259");
        addSubscription(apiStores.login(paramsMap), new ApiCallback<UserNet>() {
            @Override
            public void onSuccess(UserNet model) {
                RecordApplication.getInstance().getUser().setName(model.getData().getNickname());
                RecordApplication.getInstance().getUser().setToken(model.getData().getAccess_token());
                login(true);
            }

            @Override
            public void onFailure(int errorCode) {
                // clear user data
                RecordApplication.getInstance().setUser(null);
                login(false);
            }
        });
    }
}

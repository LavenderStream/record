package org.record.tiny.demo.main;

import org.record.tiny.base.BaseView;
import org.record.tiny.demo.model.Tab;

import java.util.List;

@SuppressWarnings("All")
public class MainContract {
    interface View extends BaseView {
        void getTabs(List<Tab.DataBean> tabs);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void checkPermissions(boolean success);

        void login(boolean success);

        void getTabs(boolean success, List<Tab.DataBean> tabs);
    }
}

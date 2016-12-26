package org.record.tiny.demo.follow;

import org.record.tiny.base.BaseView;

public class FollowContract {

    interface View extends BaseView {
        void addCollection();

        void removeCollection();

        void initCollection(boolean isColl, String str);

        void getWeb(String webUrl);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void add();

        void remove();
    }

}

package org.record.tiny.demo.follow;

import org.record.tiny.base.BaseView;

public class FollowContract {

    interface View extends BaseView {
        void addCollection();

        void removeCollection();

        void getWeb(String webUrl);

        void getCollectionState(boolean isCollection);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void add(String storyId);

        void remove(String storyId);
    }

}

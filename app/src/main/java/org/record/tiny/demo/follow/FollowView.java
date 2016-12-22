package org.record.tiny.demo.follow;

import org.record.tiny.base.BaseView;

public interface FollowView extends BaseView {

    void getTitle(String title);

    void getFollow(boolean isFollow);

    void getWeb(String webUrl);

    void showLoading();

    void hideLoading();
}

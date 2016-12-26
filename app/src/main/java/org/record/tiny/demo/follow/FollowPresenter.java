package org.record.tiny.demo.follow;

import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.CollectionRealm;
import org.record.tiny.demo.model.User;
import org.record.tiny.demo.ui.adapter.StoryRecyclerViewAdapter;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.EventIntent;
import org.record.tiny.utils.RealmUtils;

import io.realm.Realm;
import okhttp3.ResponseBody;

@SuppressWarnings("All")
public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter {
    // 保存在分别的收藏数据
    private CollectionRealm mCollectionRealm;
    // 点击查看的文章id
    private String mArticleId;

    public FollowPresenter(FollowContract.View mvpView) {
        super(mvpView);
    }

    @Override
    public void start() {
        mArticleId = (String) EventIntent.get("article_id");
        LogUtils.d("FollowPresenter -> start articleId: " + mArticleId);
        User user = RecordApplication.getInstance().getUser();
        if (user == null) {
            mvpView.getCollectionState(false);
        } else {
            mCollectionRealm = RealmUtils.getInstance().queryObjectAlls(CollectionRealm.class).contains("id", mArticleId).findFirst();
            if (mCollectionRealm == null) {
                mvpView.getCollectionState(false);
            } else {
                mvpView.getCollectionState(mCollectionRealm.isCollection());
            }
        }
        getWebUrl();
    }

    @Override
    public void add(String storyId) {
        mvpView.showLoading();
        String token = RecordApplication.getInstance().getUser().getToken();
        if (!TextUtils.isEmpty(token)) {
            addSubscription(apiStores.addCollection(storyId, token), new ApiCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody model) {
                    Realm.getDefaultInstance().beginTransaction();
                    if (mCollectionRealm == null) {
                        mCollectionRealm.setId(Integer.valueOf(mArticleId));
                    }
                    mCollectionRealm.setCollection(true);
                    Realm.getDefaultInstance().commitTransaction();

                    mvpView.addCollection();
                    mvpView.hideLoading();
                }

                @Override
                public void onFailure(int errorCode) {
                    mvpView.hideLoading();
                    mvpView.error(Error.UNKNOWN_NET_ERROR);
                }
            });
        } else {
            mvpView.hideLoading();
            mvpView.error(Error.UNKNOWN_NET_ERROR);
        }

    }

    @Override
    public void remove(String storyId) {
        mvpView.showLoading();
        String token = RecordApplication.getInstance().getUser().getToken();
        if (!TextUtils.isEmpty(token)) {
            addSubscription(apiStores.removeCollection(storyId, token), new ApiCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody model) {

                    Realm.getDefaultInstance().beginTransaction();
                    if (mCollectionRealm == null) {
                        mCollectionRealm.setId(Integer.valueOf(mArticleId));
                    }
                    mCollectionRealm.setCollection(false);
                    Realm.getDefaultInstance().commitTransaction();

                    mvpView.removeCollection();
                    mvpView.hideLoading();
                }

                @Override
                public void onFailure(int errorCode) {
                    mvpView.hideLoading();
                    mvpView.error(Error.UNKNOWN_NET_ERROR);
                }
            });
        } else {
            mvpView.hideLoading();
            mvpView.error(Error.UNKNOWN_NET_ERROR);
        }
    }

    private void getWebUrl() {
        String webUrl = (String) EventIntent.get("web_url");
        if (TextUtils.isEmpty(webUrl))
            mvpView.getWeb(StoryRecyclerViewAdapter.S_DEFAULT_IMAGE);
        else
            mvpView.getWeb(webUrl);
    }

}

package org.record.tiny.demo.follow;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.User;
import org.record.tiny.demo.ui.adapter.StoryRecyclerViewAdapter;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.EventIntent;

import java.util.Set;

import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;

@SuppressWarnings("All")
public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter {
    // 点击查看的文章id
    private String mArticleId;
    private Set<String> mArticleIdSet = Sets.newHashSet();

    public FollowPresenter(FollowContract.View mvpView) {
        super(mvpView);
    }

    @Override
    public void start() {
        getArtcleId();
        mArticleId = (String) EventIntent.get("article_id");

        User user = RecordApplication.getInstance().getUser();
        if (user == null) {
            mvpView.initCollection(false, "收藏");
        } else {
            if (mArticleIdSet.contains(mArticleId)) {
                mvpView.initCollection(true, "取消收藏");
            } else {
                mvpView.initCollection(false, "收藏");
            }
        }
        getWebUrl();
    }

    @Override
    public void add() {
        mvpView.showLoading();
        String token = RecordApplication.getInstance().getUser().getToken();
        if (!TextUtils.isEmpty(token)) {
            addSubscription(apiStores.addCollection(mArticleId, token), new ApiCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody model) {
                    LogUtils.d("FollowPresenter -> onSuccess: " + model);

                    mArticleIdSet.add(mArticleId);
                    saveArtcleId();

                    mvpView.addCollection();
                    mvpView.hideLoading();
                }

                @Override
                public void onFailure(int errorCode) {
                    LogUtils.d("FollowPresenter -> onFailure: ");
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
    public void remove() {
        mvpView.showLoading();
        String token = RecordApplication.getInstance().getUser().getToken();
        if (!TextUtils.isEmpty(token)) {
            addSubscription(apiStores.removeCollection(mArticleId, token), new ApiCallback<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody model) {
                    LogUtils.d("FollowPresenter -> onSuccess: ");
                    mArticleIdSet.remove(mArticleId);
                    saveArtcleId();

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

    private void getArtcleId() {
        SharedPreferences sp = RecordApplication.getContext().getSharedPreferences("fav", MODE_PRIVATE);
        Gson gson = new Gson();
        mArticleIdSet.clear();

        Set<String> artcleIdSet = gson.fromJson(sp.getString("fav_set_string", ""), new TypeToken<Set<String>>() {
        }.getType());
        if (artcleIdSet == null)
            return;
        mArticleIdSet.clear();
        mArticleIdSet.addAll(artcleIdSet);

        for (String s : mArticleIdSet) {
            LogUtils.d("FollowPresenter -> getArtcleId: " + s);
        }
    }

    private void saveArtcleId() {
        SharedPreferences sp = RecordApplication.getContext().getSharedPreferences("fav", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        LogUtils.d("FollowPresenter -> saveArtcleId: " + gson.toJson(mArticleIdSet));
        editor.putString("fav_set_string", gson.toJson(mArticleIdSet));
        editor.apply();
    }

    @Override
    public void detachView() {
        super.detachView();
        saveArtcleId();
    }
}

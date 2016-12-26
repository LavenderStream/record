package org.record.tiny.demo.favorite;

import android.text.TextUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.Favorite;
import org.record.tiny.demo.model.FavoriteNet;
import org.record.tiny.net.ApiCallback;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.Error;

import java.util.List;
import java.util.Map;

@SuppressWarnings("All")
public class FavoritePresenter extends BasePresenter<FavoriteContract.View> implements FavoriteContract.Presenter {
    public FavoritePresenter(FavoriteContract.View mvpView) {
        super(mvpView);
    }

    public void start(int page) {
        mvpView.loading();
        Map<String, String> paramsMap = Maps.newHashMap();
        String token = RecordApplication.getInstance().getUser().getToken();
        if (TextUtils.isEmpty(token)) {
            mvpView.error(Error.UNKNOWN_NET_ERROR);
            return;
        }
        paramsMap.put("access_token", token);
        paramsMap.put("page", String.valueOf(page));
        paramsMap.put("page_size", "10");

        addSubscription(apiStores.getFavorite(paramsMap), new ApiCallback<FavoriteNet>() {
            @Override
            public void onSuccess(FavoriteNet model) {
                List<Favorite> favorites = Lists.newArrayList();

                for (FavoriteNet.DataBean.RowsBean rowsBean : model.getData().getRows()) {
                    Favorite favorite = new Favorite();
                    favorite.setTilte(rowsBean.getTitle());
                    favorite.setImage(rowsBean.getImage());

                    favorites.add(favorite);
                }

                mvpView.getFavorites(favorites);
                mvpView.hideLoading();
            }

            @Override
            public void onFailure(int errorCode) {
                mvpView.error(Error.UNKNOWN_NET_ERROR);
                mvpView.hideLoading();
            }
        });

    }
}

package org.record.tiny.demo.favorite;

import org.record.tiny.base.BaseView;
import org.record.tiny.demo.model.Favorite;

import java.util.List;

class FavoriteContract {
    interface View extends BaseView {
        void loading();
        void hideLoading();
        void getFavorites(List<Favorite> favorites);
    }

    interface Presenter {

    }
}

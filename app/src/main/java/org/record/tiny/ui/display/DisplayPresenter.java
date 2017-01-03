package org.record.tiny.ui.display;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.ui.model.Article;
import org.record.tiny.utils.RealmUtils;

import io.realm.RealmResults;

@SuppressWarnings("All")
public class DisplayPresenter extends BasePresenter<DisplayView> {

    public DisplayPresenter(DisplayView view) {
        super(view);
    }

    public void start() {
        RealmResults articles = RealmUtils.getInstance().queryObjects(Article.class);
        mvpView.getDatas(articles);
    }
}

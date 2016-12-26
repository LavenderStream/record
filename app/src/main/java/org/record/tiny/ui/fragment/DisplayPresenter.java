package org.record.tiny.ui.fragment;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.ui.model.Article;
import org.record.tiny.utils.RealmUtils;

import io.realm.RealmResults;

@SuppressWarnings("All")
public class DisplayPresenter extends BasePresenter<DisplayView> {

    private RealmResults mArticles;

    public DisplayPresenter(DisplayView mvpView) {
        super(mvpView);
    }

    public void start() {
        mArticles = RealmUtils.getInstance().queryObjects(Article.class);
        mvpView.getDatas(mArticles);
    }
}

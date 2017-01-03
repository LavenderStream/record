package org.record.tiny.ui.display;

import org.record.tiny.base.BaseView;
import org.record.tiny.ui.model.Article;

import java.util.List;

@SuppressWarnings("All")
public interface DisplayView extends BaseView {
    /**
     * 获得文章列表
     *
     * @param articles
     */
    void getDatas(List<Article> articles);
}

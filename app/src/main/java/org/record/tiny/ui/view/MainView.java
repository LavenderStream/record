package org.record.tiny.ui.view;

import org.record.tiny.ui.model.Article;

import java.util.List;

@SuppressWarnings("All")
public interface MainView extends EditView{
    /**
     * @param articles 正文
     */
    void getContext(List<Article> articles);
}

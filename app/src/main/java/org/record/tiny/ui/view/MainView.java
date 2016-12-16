package org.record.tiny.ui.view;

import org.record.tiny.base.BaseView;
import org.record.tiny.ui.model.Article;

import java.util.List;

@SuppressWarnings("All")
public interface MainView extends BaseView {

    /**
     * @param year 年
     */
    void getYear(String year);

    /**
     * @param month 月
     */
    void getMonth(String month);

    /**
     * @param day 日
     */
    void getDay(String day);

    /**
     * @param articles 正文
     */
    void getContext(List<Article> articles);

    /**
     * @param local 地理位置
     */
    void getLocal(String local);
}

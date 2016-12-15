package org.record.tiny.ui.view;

import org.record.tiny.base.BaseView;

@SuppressWarnings("All")
public interface EditView extends BaseView {
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
     * @param local 地理位置
     */
    void getLocal(String local);
}

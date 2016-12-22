package org.record.tiny.demo.main;

import org.record.tiny.base.BaseView;
import org.record.tiny.demo.model.Tab;

import java.util.List;

@SuppressWarnings("All")
public interface MainView extends BaseView
{
    void getTabs(List<Tab.DataBean> tabs);

    void showLoading();
    void hideLoading();
}

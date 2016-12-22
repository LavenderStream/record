package org.record.tiny.demo.fragment;

import org.record.tiny.base.BaseView;
import org.record.tiny.demo.model.StoryHeaderItem;
import org.record.tiny.demo.model.StoryItem;

import java.util.List;

@SuppressWarnings("All")
public interface TabView extends BaseView {
    void getDatas(List<StoryItem> storys);

    void getHeaders(List<StoryHeaderItem> headers);

}

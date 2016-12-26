package org.record.tiny.demo.fragment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.model.StoryHeaderItem;
import org.record.tiny.demo.model.StoryItem;
import org.record.tiny.demo.model.StoryNet;
import org.record.tiny.net.ApiCallback;

import java.util.List;
import java.util.Map;

@SuppressWarnings("All")
public class TabPresenter extends BasePresenter<TabView> {
    private static final int MAX_PAGE_SIZE = 10;

    public TabPresenter(TabView mvpView) {
        super(mvpView);
    }
    /**
     * 获取每页展示的数据
     */
    public void getTabDatas(int currentPage, String id) {
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("page_size", String.valueOf(MAX_PAGE_SIZE));
        paramsMap.put("page", String.valueOf(currentPage));
        paramsMap.put("position", "top_tab_data");
        paramsMap.put("id", id);

        addSubscription(apiStores.getStorys(paramsMap), new ApiCallback<StoryNet>() {
            @Override
            public void onSuccess(StoryNet model) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(model.getData().getImgs().getData());

                List<StoryHeaderItem> storyHeaders = gson.fromJson(jsonString, new TypeToken<List<StoryHeaderItem>>() {
                }.getType());


                List<StoryItem> storyItems = Lists.newArrayList();
                for (StoryNet.DataBeanXX.StorysBean.DataBeanX dataBeanX : model.getData().getStorys().getData()) {
                    StoryItem storyItem = new StoryItem();
                    storyItem.setUserImage(dataBeanX.getUser().getSmall_avatar());
                    storyItem.setNickName(dataBeanX.getUser().getNickname());
                    storyItem.setImage(dataBeanX.getImage());
                    storyItem.setLike(dataBeanX.getLike_count());
                    storyItem.setComment(dataBeanX.getComment_count());
                    storyItem.setLink(dataBeanX.getContent_url());
                    storyItem.setArticleId(dataBeanX.getStory_id());
                    storyItems.add(storyItem);
                }
                mvpView.getHeaders(storyHeaders);
                mvpView.getDatas(storyItems);
            }

            @Override
            public void onFailure(int errorCode) {
                mvpView.error(errorCode);
            }

            @Override
            public void onFinish() {
            }
        });
    }
}

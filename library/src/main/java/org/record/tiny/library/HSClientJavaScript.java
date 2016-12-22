package org.record.tiny.library;


import android.webkit.JavascriptInterface;

public class HSClientJavaScript {

    public JSCallback listener;

    public HSClientJavaScript(JSCallback listener) {
        this.listener = listener;
    }

    public HSClientJavaScript() {
    }

    public void setListener(JSCallback listener) {
        this.listener = listener;
    }

    // js通知客户端，当前光标定位在标题处
    // 客户端处理底部栏目按钮全部置灰，不可点击。
    @JavascriptInterface
    public void hsFocusTitle(String bool) {
        if (listener != null) {
            listener.hsFocusTitle(bool);

        }
    }

    // js通知客户端，当前光标处是否有引用，客户端引用按钮UI变化
    // isQuote 传 1：存在引用  0：不存在
    @JavascriptInterface
    public void hsFocusBlockQuote(String isQuote) {
        if (listener != null) {
            listener.hsFocusBlockQuote(isQuote);

        }
    }

    // js通知客户端，当前字体状态状态，客户端字体按钮UI变化
    // fontSize 1:大字体 0：小字体
    @JavascriptInterface
    public void hsFocusFont(String fontSize) {
        if (listener != null) {
            listener.hsFocusFont(fontSize);

        }
    }

    // 客户端得到html数据
    @JavascriptInterface
    public void setJSHtml(String html) {
//            LogUtil.w("客户端得到html数据-setJSHtml-:" + html);
        if (listener != null) {
            listener.setJSHtml(html);
        }
    }

    // js传递json参数给客户端，客户端得到sign和token，再回掉js方法，传出sign和token。
    @JavascriptInterface
    public void setReqParam(String json, String callbackName) {
        if (listener != null) {
            listener.setReqParam(json, callbackName);
        }
    }

    // 打开模板html，js通知客户端，页面加载完成。
    // 防止js未完成初始化，客户端调用js对象出错。
    @JavascriptInterface
    public void notifyPageFinish() {
        if (listener != null) {
            listener.notifyPageFinish();
        }
    }

    @JavascriptInterface
    public void showKeyBoard(String isShow) {
        if (listener != null) {
            listener.showKeyBoard(isShow);
        }
    }

    // 关注操作成功  "0" 未关注 "1" 已关注
    @JavascriptInterface
    public void isFollowSuccess(String isFollowSuccess, String uid) {
        if (listener != null) {
            listener.isFollowSuccess(isFollowSuccess, uid);
        }
    }

    @JavascriptInterface
    public void notifyLoginCall(String error_code) {
        if (listener != null) {
            listener.notifyLoginCall(error_code);
        }
    }

    /**
     * @param eventId "jl_start":起个头
     *                "jl_start_drop":不玩了
     *                "jl_start_done":起好了
     *                "jl_pick":接个龙
     *                "jl_pick_change":换一个
     *                "jl_pick_continue":后面继续
     *                "jl_mine":我的接龙
     *                "jl_phs":品花生
     *                "storydetail_fl":故事详情页关注
     */
    @JavascriptInterface
    public void notifyStatistics(String eventId, String params) {
        if (listener != null)
            listener.notifyStatistics(eventId, params);
    }

    /**
     * 内容页，点击评论
     *
     * @param callbackname JS回调用方法名
     */
    @JavascriptInterface
    public void hsCommentClick(String params, String callbackname) {
        if (listener != null)
            listener.hsCommentClick(params, callbackname);
    }

    /**
     * 内容页，1 点赞  0 取消点赞
     */
    @JavascriptInterface
    public void hsAddLike(String state) {
        if (listener != null)
            listener.hsAddLike(state);
    }

    @JavascriptInterface
    public String hsReqNetworkState() {
        if (listener != null)
            return listener.hsReqNetworkState();
        else
            return "1";
    }

    /**
     * 通知H5加载完成， 0 是成功  1是失败
     *
     * @param state
     */
    @JavascriptInterface
    public void notifyInvokeCROSS(int state) {
        if (listener != null)
            listener.notifyInvokeCROSS(state);
    }

    @JavascriptInterface
    public void hsNotifyExit() {
        if (listener != null)
            listener.hsNotifyExit();
    }

    /**
     * 通知显示上下菜单栏， true 是显示  false是不显示
     *
     * @param isShow
     */
    @JavascriptInterface
    public void showNavBar(boolean isShow) {
        if (listener != null)
            listener.showNavBar(isShow);
    }

    /**
     * 新故事接龙点击分享， params 返回分享json
     *
     * @param params
     */
    @JavascriptInterface
    public void hsRelayShare(String params) {
        if (listener != null)
            listener.hsRelayShare(params);
    }

    /**
     * 新的故事接龙 回调获取客户端的token
     */
    @JavascriptInterface
    public void hsNeedLogin() {
        if (null != listener)
            listener.hsNeedLogin();
    }

    /**
     * 新的故事接龙 处理页面加载完之后的状态，用于back键回退。如加载失败，退出键退出无效
     */
    @JavascriptInterface
    public void hsJLPageLoadComplete() {
        if (null != listener)
            listener.hsJLPageLoadComplete();
    }

    /**
     * 1.5版本 预览添加图片回调
     */
    @JavascriptInterface
    public void hsPreviewPicsCall(String json, String callName) {
        if (null != listener)
            listener.hsPreviewPicsCall(json, callName);
    }

    /**
     * 1.5版本 写作页字数限制
     */
    @JavascriptInterface
    public void hsOnContentChange(String textCount) {
        if (null != listener)
            listener.hsOnContentChange(textCount);
    }
}

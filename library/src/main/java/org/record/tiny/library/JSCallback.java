package org.record.tiny.library;

/**
 * Created by tiny on 12/22/2016.
 */

public interface JSCallback {
    public void hsFocusTitle(String bool);

    public void hsFocusBlockQuote(String isQuote);

    public void hsFocusFont(String fontSize);

    public void setJSHtml(String html);

    public void setReqParam(String json, String callbackName);

    public void notifyPageFinish();

    public void showKeyBoard(String isShow);

    public void isFollowSuccess(String isFollowSuccess, String uid);

    public void notifyLoginCall(String error_code);

    public void notifyStatistics(String eventId, String params);

    public void hsCommentClick(String params, String callbackname);

    public void hsAddLike(String state);

    public String hsReqNetworkState();

    public void notifyInvokeCROSS(int state);

    public void hsNotifyExit();

    public void showNavBar(boolean isShow);

    public void hsRelayShare(String params);

    public void hsNeedLogin();

    public void hsJLPageLoadComplete();

    public void hsPreviewPicsCall(String json, String callName);

    public void hsOnContentChange(String textCount);
}

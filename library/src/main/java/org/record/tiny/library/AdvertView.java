package org.record.tiny.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

/**
 * Created by 碧桃鹦鹉 on 2016/8/23.
 */
public class AdvertView extends RelativeLayout {
    //请求网页超时时间
    private Context mContext;
    private WebView webView;
    private boolean isUploadOver = false;


    public AdvertView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public AdvertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        webView = new WebView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        webView.setLayerType(LAYER_TYPE_SOFTWARE, null);
        addView(webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        HSClientJavaScript javaScript = new HSClientJavaScript();
        javaScript.setListener(new Callback() {
            @Override
            public void isFollowSuccess(String isFollowSuccess, String uid) {
                super.isFollowSuccess(isFollowSuccess, uid);
                Log.d("1111", "isFollowSuccess: " + isFollowSuccess + " : " + uid);
            }
        });
        webView.addJavascriptInterface(javaScript, "HSClient");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                view.loadUrl(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
        webSettings.setDefaultTextEncodingName("utf-8");
        //webView.addJavascriptInterface(javaScriptInterface, "mkmyle");
    }

    public void load(final String url) {
        webView.loadUrl(url);
    }

    class Callback implements JSCallback {

        @Override
        public void hsFocusTitle(String bool) {

        }

        @Override
        public void hsFocusBlockQuote(String isQuote) {

        }

        @Override
        public void hsFocusFont(String fontSize) {

        }

        @Override
        public void setJSHtml(String html) {

        }

        @Override
        public void setReqParam(String json, String callbackName) {

        }

        @Override
        public void notifyPageFinish() {

        }

        @Override
        public void showKeyBoard(String isShow) {

        }

        @Override
        public void isFollowSuccess(String isFollowSuccess, String uid) {

        }

        @Override
        public void notifyLoginCall(String error_code) {

        }

        @Override
        public void notifyStatistics(String eventId, String params) {

        }

        @Override
        public void hsCommentClick(String params, String callbackname) {

        }

        @Override
        public void hsAddLike(String state) {

        }

        @Override
        public String hsReqNetworkState() {
            return null;
        }

        @Override
        public void notifyInvokeCROSS(int state) {

        }

        @Override
        public void hsNotifyExit() {

        }

        @Override
        public void showNavBar(boolean isShow) {

        }

        @Override
        public void hsRelayShare(String params) {

        }

        @Override
        public void hsNeedLogin() {

        }

        @Override
        public void hsJLPageLoadComplete() {

        }

        @Override
        public void hsPreviewPicsCall(String json, String callName) {

        }

        @Override
        public void hsOnContentChange(String textCount) {

        }
    }
}

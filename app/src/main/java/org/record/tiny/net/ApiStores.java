package org.record.tiny.net;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

@SuppressWarnings("All")
public interface ApiStores {
    public static String API_SERVER_URL = "http://www.baidu.com/";

    @GET("/")
    Flowable<ResponseBody> getText();
}

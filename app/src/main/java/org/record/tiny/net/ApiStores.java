package org.record.tiny.net;

import org.record.tiny.demo.model.FavoriteNet;
import org.record.tiny.demo.model.StoryNet;
import org.record.tiny.demo.model.Tab;
import org.record.tiny.demo.model.UserNet;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

@SuppressWarnings("All")
public interface ApiStores {
    public static String API_SERVER_URL = "http://221.179.193.164/";

    @GET("huasheng/client/operation/show.json")
    Observable<Tab> getTabs(@Query("position") String position);

    @GET("huasheng/client/operation/show.json")
    Observable<StoryNet> getStorys(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("huasheng/client/fav/add.json")
    Observable<ResponseBody> addCollection(@Field("story_id") String stotyId, @Field("access_token") String token);

    @FormUrlEncoded
    @POST("huasheng/client/fav/remove.json")
    Observable<ResponseBody> removeCollection(@Field("story_id") String stotyId, @Field("access_token") String token);

    @FormUrlEncoded
    @POST("huasheng/client/user/login.json")
    Observable<UserNet> login(@FieldMap Map<String, String> params);


    @GET("huasheng/client/user/fav_list.json")
    Observable<FavoriteNet> getFavorite(@QueryMap Map<String, String> params);
}

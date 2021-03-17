package com.example.android_practice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("/{path}/list/408/1/json?1=1")
    Call<ResponseBody> getTestGet(@Path("path") String path, @Query("k") String k);

    @FormUrlEncoded
    @POST("/{path}/list/405/1/json")
    Call<ResponseBody> getTestPost(@Path("path") String path, @Field("k") String k);
}

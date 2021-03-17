package com.example.android_practice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("/{path}/list/408/1/json?1=1")
    Call<ResponseBody> getTest(@Path("path") String path, @Query("k") String k);
}

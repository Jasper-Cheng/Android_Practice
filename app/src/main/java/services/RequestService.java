package services;

import bean.TestBean;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestService {
    @GET("/{path}/list/405/1/json")
    Observable<TestBean> getTestPost(@Path("path") String path);
}

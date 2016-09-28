package derry.assessmenttest.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiService {

    @GET("user")
    Call<List<User>> getResultInfo();

    @GET("user")
    Call<ResponseBody> getResultAsJSON();
}

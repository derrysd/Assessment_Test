package derry.assessmenttest.utils;

import java.util.List;

import derry.assessmenttest.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

}

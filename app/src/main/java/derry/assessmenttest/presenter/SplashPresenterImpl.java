package derry.assessmenttest.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import derry.assessmenttest.model.User;
import derry.assessmenttest.model.UserApiService;
import derry.assessmenttest.view.SplashView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashPresenterImpl implements SplashPresenter {
    SplashView splashView;
    Boolean isStarted = false;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void onWindowFocusChanged() {
        if(!isStarted){
            isStarted = true;
            splashView.startAnim();
        }
    }

    @Override
    public void retrieveData(final SharedPreferences preferences, final String tag) {
        String savedString = preferences.getString(tag, "");
        if (savedString.equals("")){
            splashView.showLoading();
            //todo ambil data json
            Log.i("LOG_INFO", "Starting Retrieving JSON");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserApiService service =  retrofit.create(UserApiService.class);
            Call<List<User>> call = service.getResultInfo();

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.i("LOG_INFO", "Success to retrieve JSON");

                    List<User> users = response.body();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    String toSave = gson.toJson(users);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(tag, toSave).apply();

                    splashView.hideLoading();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.e("LOG_ERROR", "Failed to retrieve JSON");
                    // TODO: 28/09/2016  buat dialog network failed
                    splashView.hideLoading();
                }
            });
        } else {
            Log.i("LOG_INFO", "JSON exist in Sharedpreferences");
        }


    }

}

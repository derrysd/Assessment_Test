package derry.assessmenttest.models;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import derry.assessmenttest.entities.User;
import derry.assessmenttest.utils.UserAPIClient;
import derry.assessmenttest.utils.UserApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersModelImpl implements UsersModel {
    private SharedPreferences preferences;
    private static final String TAG_SAVEDSTRING = "users";

    public UsersModelImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean isSavedJSONExist() {
        String savedString = preferences.getString(TAG_SAVEDSTRING, "");
        return !savedString.equals("");
    }

    @Override
    public void getDataFromAPI(final OnFetchDataListener listener) {
        Log.i("LOG_INFO", "Starting Retrieving JSON From API");
        UserApiInterface userApiService = UserAPIClient.getClient().create(UserApiInterface.class);
        Call<List<User>> call = userApiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("LOG_INFO", "Success to retrieve JSON");

                if(response.isSuccessful()){
                    List<User> result = response.body();
                    Log.i("LOG_INFO", "Total User: " + result.size());
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    String toSave = gson.toJson(result);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(TAG_SAVEDSTRING, toSave).apply();
                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Log.i("LOG_INFO", "Error : " + jObjError.getString("message"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.i("LOG_INFO", "Error : " + e.getMessage());
                    }
                }
                listener.onFetchDataSuccess();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("LOG_ERROR", "Failed to retrieve JSON");
                listener.onFetchDataFailure(t.toString());
            }
        });

    }

    @Override
    public List<User> getDataFromSharedPref() {
        String json = preferences.getString(TAG_SAVEDSTRING, "");
        return new Gson().fromJson(json, new TypeToken<List<User>>(){}.getType());
    }
}

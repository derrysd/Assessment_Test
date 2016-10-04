package derry.assessmenttest.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import derry.assessmenttest.models.UsersModel;
import derry.assessmenttest.models.UsersModelImpl;
import derry.assessmenttest.views.SplashView;

public class SplashPresenterImpl implements SplashPresenter, UsersModel.OnFetchDataListener{
    private SplashView splashView;
    private UsersModel usersModel;
    private Boolean isStarted = false;

    public SplashPresenterImpl(SplashView splashView, SharedPreferences preferences) {
        this.splashView = splashView;
        this.usersModel = new UsersModelImpl(preferences);
    }

    @Override
    public void onWindowFocusChanged() {
        if(!isStarted){
            Log.i("LOG_INFO", "Splash screen Started");
            isStarted = true;
            splashView.startAnim();
        }
    }

    @Override
    public void initData() {
        if (usersModel.isSavedJSONExist()){
            Log.i("LOG_INFO", "JSON Exist in SharedPref");
            splashView.goToMainActivity();
        } else {
            Log.i("LOG_INFO", "JSON Not Exist");
            splashView.showLoading();
            usersModel.getDataFromAPI(this);
        }

    }

    @Override
    public void onFetchDataSuccess() {
        splashView.hideLoading();
        splashView.goToMainActivity();
    }

    @Override
    public void onFetchDataFailure(String message) {
        Log.e("LOG_INFO", "Error Load JSON from API: " + message);
        splashView.showErrorFetchDialog(message);
        splashView.hideLoading();
    }


}

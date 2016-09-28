package derry.assessmenttest.presenter;

import android.content.SharedPreferences;

public interface SplashPresenter {

    void onWindowFocusChanged();

    void retrieveData(SharedPreferences preferences, String tag);
}

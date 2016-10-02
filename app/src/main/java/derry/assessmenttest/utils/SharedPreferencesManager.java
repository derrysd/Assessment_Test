package derry.assessmenttest.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesManager {
    private static final String TAG_SHAREDPREFS = "derry.assessmenttest";
    private static SharedPreferencesManager instance;
    private SharedPreferences preferences;

    private SharedPreferencesManager(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences(TAG_SHAREDPREFS, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager createInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);
        return instance;
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }
}

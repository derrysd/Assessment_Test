package derry.assessmenttest.models;


import java.util.List;

import derry.assessmenttest.entities.User;

public interface UsersModel {
    boolean isSavedJSONExist();

    interface OnFetchDataListener {
        void onFetchDataSuccess();
        void onFetchDataFailure(String message);
    }

    void getDataFromAPI(OnFetchDataListener listener);
    List<User> getDataFromSharedPref();
}

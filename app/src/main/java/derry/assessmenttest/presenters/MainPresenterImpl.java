package derry.assessmenttest.presenters;

import android.content.SharedPreferences;

import java.util.List;

import derry.assessmenttest.entities.User;
import derry.assessmenttest.models.UsersModel;
import derry.assessmenttest.models.UsersModelImpl;
import derry.assessmenttest.views.MainView;

public class MainPresenterImpl implements MainPresenter {
    MainView mainView;
    UsersModel usersModel;

    public MainPresenterImpl(MainView mainView, SharedPreferences preferences) {
        this.mainView = mainView;
        this.usersModel = new UsersModelImpl(preferences);
    }

    @Override
    public List<User> getData() {
        return usersModel.getDataFromSharedPref();
    }
}

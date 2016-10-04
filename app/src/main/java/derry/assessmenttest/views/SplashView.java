package derry.assessmenttest.views;

public interface SplashView {

    void startAnim();
    void showLoading();
    void hideLoading();
    void goToMainActivity();
    void showErrorFetchDialog(String errorMessage);
}

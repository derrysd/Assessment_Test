package derry.assessmenttest.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import derry.assessmenttest.R;
import derry.assessmenttest.presenter.SplashPresenter;
import derry.assessmenttest.presenter.SplashPresenterImpl;

public class SplashActivity extends AppCompatActivity implements SplashView{
    ProgressBar progressBar;
    SplashPresenter presenter;
    ImageView logo;
    SharedPreferences prefs;

    static final String TAG_SHAREDPREFS = "derry.assessmenttest";
    static final String TAG_SAVEDSTRING = "users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        logo = (ImageView) findViewById(R.id.logo);

        presenter = new SplashPresenterImpl(this);

        prefs = getSharedPreferences(TAG_SHAREDPREFS, MODE_PRIVATE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        presenter.onWindowFocusChanged();
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void startAnim() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_fade_in);
        logo.startAnimation(fadeInAnimation);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                presenter.retrieveData(prefs, TAG_SAVEDSTRING);
                logo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}

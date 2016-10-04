package derry.assessmenttest.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import derry.assessmenttest.R;
import derry.assessmenttest.presenters.SplashPresenter;
import derry.assessmenttest.presenters.SplashPresenterImpl;
import derry.assessmenttest.utils.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity implements SplashView{
    private ProgressBar progressBar;
    private SplashPresenter presenter;
    private ImageView logo;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loadingText = (TextView) findViewById(R.id.loadingText);
        logo = (ImageView) findViewById(R.id.logo);
        SharedPreferences prefs = SharedPreferencesManager.createInstance(getApplicationContext()).getPreferences();
        presenter = new SplashPresenterImpl(this, prefs);
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
                presenter.initData();
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
        loadingText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showErrorFetchDialog(String errorMessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogStyle);
        builder.setTitle(R.string.dialog_error_title);
        builder.setMessage(R.string.dialog_error_message);
        builder.setPositiveButton(R.string.dialog_error_retry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.initData();
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton(R.string.dialog_error_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onBackPressed() {
    }
}

package com.shimirokach.bankingapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.ui.launch.LaunchingActivity;
import com.shimirokach.bankingapp.ui.main.MainActivity;

/**
 * The type Splash activity.
 */
public class SplashActivity extends AppCompatActivity implements SplashCallBack {

    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        viewModel.setSplashCallBack(this);
    }

    @Override
    public void onLoaded(boolean isLogin) {
        if (isLogin)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LaunchingActivity.class));

        finish();
    }
}

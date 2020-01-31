package com.shimirokach.bankingapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.ui.home.HomeActivity;
import com.shimirokach.bankingapp.ui.launch.LaunchingActivity;

public class SplashActivity extends AppCompatActivity implements SplashNavigator {

    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        viewModel.setSplashNavigator(this);
    }

    @Override
    public void onLoaded(boolean isLogin) {
        if (isLogin)
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LaunchingActivity.class));

        finish();
    }
}

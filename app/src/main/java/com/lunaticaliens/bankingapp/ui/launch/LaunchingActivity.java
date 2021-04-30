package com.lunaticaliens.bankingapp.ui.launch;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.lunaticaliens.bankingapp.R;
import com.lunaticaliens.bankingapp.databinding.ActivityLandingBinding;
import com.lunaticaliens.bankingapp.ui.auth.LoginActivity;
import com.lunaticaliens.bankingapp.ui.auth.RegisterActivity;

public class LaunchingActivity extends AppCompatActivity implements LaunchPageCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLandingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_landing);

        LaunchViewModel viewModel = new ViewModelProvider(this).get(LaunchViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setLaunchPageCallBack(this);
    }

    @Override
    public void onLogin() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onRegistration() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}

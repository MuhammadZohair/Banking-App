package com.shimirokach.bankingapp.ui.launch;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.databinding.ActivityLandingBinding;
import com.shimirokach.bankingapp.ui.auth.LoginActivity;
import com.shimirokach.bankingapp.ui.auth.RegisterActivity;

/**
 * The type Launching activity.
 */
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

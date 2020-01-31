package com.shimirokach.bankingapp.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.databinding.ActivityRegisterBinding;
import com.shimirokach.bankingapp.ui.home.HomeActivity;
import com.shimirokach.bankingapp.utils.Utils;

public class RegisterActivity extends AppCompatActivity implements AuthListener {

    private AuthViewModel viewModel;
    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setAuthListener(this);
    }

    @Override
    public void onStarted() {
        binding.setIsLoading(true);
    }

    @Override
    public void onSuccess() {
        binding.setIsLoading(false);
        Utils.success(getApplicationContext(), "Account created successfully");
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    @Override
    public void onFailure(String message) {
        Utils.info(getApplicationContext(), message);
        binding.setIsLoading(false);
    }
}

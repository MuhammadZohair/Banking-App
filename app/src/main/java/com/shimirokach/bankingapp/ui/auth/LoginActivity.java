package com.shimirokach.bankingapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.databinding.ActivityLoginBinding;
import com.shimirokach.bankingapp.ui.main.MainActivity;
import com.shimirokach.bankingapp.utils.Utils;

/**
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity implements AuthCallBack {

    private AuthViewModel viewModel;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setAuthCallBack(this);

    }

    @Override
    public void onStarted() {
        binding.setIsLoading(true);
    }

    @Override
    public void onSuccess() {
        binding.setIsLoading(false);
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Utils.success(getApplicationContext(), "Login Successful");
    }

    @Override
    public void onFailure(String message) {
        Utils.info(getApplicationContext(), message);
        binding.setIsLoading(false);
    }
}

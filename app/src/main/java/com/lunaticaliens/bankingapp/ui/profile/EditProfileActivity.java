package com.lunaticaliens.bankingapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.lunaticaliens.bankingapp.R;
import com.lunaticaliens.bankingapp.databinding.ActivityEditProfileBinding;
import com.lunaticaliens.bankingapp.ui.auth.LoginActivity;
import com.lunaticaliens.bankingapp.ui.launch.LaunchingActivity;
import com.lunaticaliens.bankingapp.utils.Utils;


/**
 * The type Edit profile activity.
 */
public class EditProfileActivity extends AppCompatActivity implements ProfileCallBack {

    private ProfileViewModel viewModel;
    private ActivityEditProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setProfileCallBack(this);

    }

    @Override
    public void onUpdateSuccessful() {
        Utils.success(getApplicationContext(), "Profile updated successfully, please login again");
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onUpdateFailure(Boolean loginFailure, String message) {
        if (loginFailure) {
            finish();
            startActivity(new Intent(this, LaunchingActivity.class));
        }
        Utils.error(this, message);
    }
}

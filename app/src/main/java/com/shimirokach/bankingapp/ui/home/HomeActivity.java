package com.shimirokach.bankingapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shimirokach.bankingapp.databinding.ActivityHomeBinding;
import com.shimirokach.bankingapp.databinding.ActivityLoginBinding;
import com.shimirokach.bankingapp.ui.auth.AuthViewModel;
import com.shimirokach.bankingapp.ui.savings.SavingsFragment;
import com.shimirokach.bankingapp.ui.transfer.TransferFragment;
import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.ui.cheque.ChequeFragment;
import com.shimirokach.bankingapp.utils.Utils;

public class HomeActivity extends AppCompatActivity implements HomeNavigator {

    private HomeViewModel viewModel;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setHomeNavigator(this);
        loadFragment(new HomeFragment());
    }

    @Override
    public void onNavigationItemClick(int item) {

        switch (item) {
            case R.id.action_dashboard:
                loadFragment(new HomeFragment());
                break;

            case R.id.action_cheque:
                loadFragment(new ChequeFragment());
                break;

            case R.id.action_transfer:
                loadFragment(new TransferFragment());
                break;

            case R.id.action_savings:
                loadFragment(new SavingsFragment());
                break;

        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}

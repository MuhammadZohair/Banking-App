package com.lunaticaliens.bankingapp.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lunaticaliens.bankingapp.R;
import com.lunaticaliens.bankingapp.databinding.ActivityMainBinding;
import com.lunaticaliens.bankingapp.ui.cheque.ChequeFragment;
import com.lunaticaliens.bankingapp.ui.home.HomeFragment;
import com.lunaticaliens.bankingapp.ui.savings.SavingsFragment;
import com.lunaticaliens.bankingapp.ui.transfer.TransferFragment;

public class MainActivity extends AppCompatActivity implements MainCallBack {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setMainCallBack(this);
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

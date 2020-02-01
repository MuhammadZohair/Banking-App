package com.shimirokach.bankingapp.ui.savings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.databinding.FragmentSavingsBinding;
import com.shimirokach.bankingapp.ui.launch.LaunchingActivity;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.Objects;

/**
 * The type Savings fragment.
 */
public class SavingsFragment extends Fragment implements SavingCallBack {


    private SavingsViewModel viewModel;
    private FragmentSavingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_savings, container, false);
        viewModel = new ViewModelProvider(this).get(SavingsViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setViewModel(viewModel);
        viewModel.setSavingCallBack(this);

        return binding.getRoot();
    }

    @Override
    public void onWithdrawSuccessful() {
        Utils.success(getContext(), "Withdrawal successful");
    }

    @Override
    public void onWithdrawFailure(Boolean loginFailure, String message) {
        if (loginFailure) {
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), LaunchingActivity.class));
        }
        Utils.error(getContext(), message);

    }

    @Override
    public void onDepositSuccessful() {
        Utils.success(getContext(), "Deposit successful");
    }

    @Override
    public void onDepositFailure(Boolean loginFailure, String message) {
        if (loginFailure) {
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), LaunchingActivity.class));
        }
        Utils.error(getContext(), message);

    }
}

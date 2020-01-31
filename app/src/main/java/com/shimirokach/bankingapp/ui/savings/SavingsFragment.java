package com.shimirokach.bankingapp.ui.savings;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shimirokach.bankingapp.R;

public class SavingsFragment extends Fragment {

    private SavingsViewModel mViewModel;

    public static SavingsFragment newInstance() {
        return new SavingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        SavingsDialog savingsDialog = new SavingsDialog(getActivity(), false);
        savingsDialog.show();

        return inflater.inflate(R.layout.fragment_savings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SavingsViewModel.class);
        // TODO: Use the ViewModel
    }

}

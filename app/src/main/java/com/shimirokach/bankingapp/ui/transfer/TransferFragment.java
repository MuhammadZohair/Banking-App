package com.shimirokach.bankingapp.ui.transfer;

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
import com.shimirokach.bankingapp.databinding.FragmentTransferBinding;
import com.shimirokach.bankingapp.ui.launch.LaunchingActivity;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.Objects;

/**
 * The type Transfer fragment.
 */
public class TransferFragment extends Fragment implements TransferCallBack {

    private TransferViewModel viewModel;
    private FragmentTransferBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transfer, container, false);
        viewModel = new ViewModelProvider(this).get(TransferViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setViewModel(viewModel);
        viewModel.setTransferCallBack(this);

        return binding.getRoot();
    }

    @Override
    public void onTransferSuccessful() {
        Utils.success(getContext(), "Transferred successfully");
    }

    @Override
    public void onFailure(Boolean loginFailure, String message) {
        if (loginFailure) {
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), LaunchingActivity.class));
        }
        Utils.error(getContext(), message);
    }
}

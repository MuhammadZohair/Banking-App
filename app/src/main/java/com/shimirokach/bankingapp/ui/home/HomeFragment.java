package com.shimirokach.bankingapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.databinding.FragmentHomeBinding;
import com.shimirokach.bankingapp.ui.auth.LoginActivity;
import com.shimirokach.bankingapp.ui.profile.EditProfileActivity;
import com.shimirokach.bankingapp.utils.SessionManager;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.List;
import java.util.Objects;

/**
 * The type Home fragment.
 */
public class HomeFragment extends Fragment implements HomeCallBack, Observer<List<Transactions>> {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    private TransactionAdapter transactionAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setViewmodel(viewModel);
        viewModel.setHomeCallBack(this);
        viewModel.getAllTransactions().observe(getViewLifecycleOwner(), this);

        View v = binding.getRoot();

        RecyclerView recyclerView = v.findViewById(R.id.rv_transactions);
        transactionAdapter = new TransactionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(transactionAdapter);

        return v;
    }

    @Override
    public void onEditClicked(View view) {

        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }

    @Override
    public void onLogoutClicked(View view) {
        Objects.requireNonNull(getActivity()).finish();
        SessionManager.getInstance().expireToken();
        Utils.success(getContext(), "Logged out");
        startActivity(new Intent(getContext(), LoginActivity.class));

    }

    @Override
    public void onChanged(List<Transactions> transactions) {
        transactionAdapter.setTransactionList(transactions);

        if (transactions.isEmpty())
            binding.setIsEmpty(true);
    }
}

package com.lunaticaliens.bankingapp.ui.savings;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lunaticaliens.bankingapp.data.Repository;
import com.lunaticaliens.bankingapp.data.local.entities.User;
import com.lunaticaliens.bankingapp.utils.SessionManager;

import java.util.concurrent.ExecutionException;

public class SavingsViewModel extends AndroidViewModel {

    public String currentBalance = "";
    public String amount = "";
    private final Repository repository;
    private SavingCallBack savingCallBack;

    public SavingsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            currentBalance = user.getSavings() + "";

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setSavingCallBack(SavingCallBack savingCallBack) {
        this.savingCallBack = savingCallBack;
    }

    public void onWithdrawButtonClicked(View v) {
        v.setEnabled(false);

        if (amount.isEmpty()) {
            savingCallBack.onWithdrawFailure(false, "Please enter an amount");
            v.setEnabled(true);
            return;
        }

        Double finalAmount = Double.parseDouble(amount);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            if (user.getSavings() >= finalAmount) {
                user.setBalance(user.getBalance() + finalAmount);
                user.setSavings(user.getSavings() - finalAmount);
                repository.updateUser(user);
                savingCallBack.onWithdrawSuccessful();
            } else {
                savingCallBack.onWithdrawFailure(false, "No sufficient balance");
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            SessionManager.getInstance().expireToken();
            savingCallBack.onWithdrawFailure(true, "User session expired, please login again");
        } finally {
            v.setEnabled(true);
        }

    }

    public void onDepositButtonClicked(View v) {
        v.setEnabled(false);

        if (amount.isEmpty()) {
            savingCallBack.onDepositFailure(false, "Please enter an amount");
            v.setEnabled(true);
            return;
        }

        Double finalAmount = Double.parseDouble(amount);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            if (user.getBalance() >= finalAmount) {
                user.setBalance(user.getBalance() - finalAmount);
                user.setSavings(user.getSavings() + finalAmount);
                repository.updateUser(user);
                savingCallBack.onDepositSuccessful();
            } else {
                savingCallBack.onDepositFailure(false, "No sufficient balance");
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            SessionManager.getInstance().expireToken();
            savingCallBack.onDepositFailure(true, "User session expired, please login again");
        } finally {
            v.setEnabled(true);
        }
    }
}

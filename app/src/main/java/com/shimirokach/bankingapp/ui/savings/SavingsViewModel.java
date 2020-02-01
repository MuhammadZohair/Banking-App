package com.shimirokach.bankingapp.ui.savings;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;

import java.util.concurrent.ExecutionException;

/**
 * The type Savings view model.
 */
public class SavingsViewModel extends AndroidViewModel {

    /**
     * The Current balance.
     */
    public String currentBalance = "";
    /**
     * The Amount.
     */
    public String amount = "";
    private Repository repository;
    private SavingCallBack savingCallBack;

    /**
     * Instantiates a new Savings view model.
     *
     * @param application the application
     */
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

    /**
     * Sets saving call back.
     *
     * @param savingCallBack the saving call back
     */
    void setSavingCallBack(SavingCallBack savingCallBack) {
        this.savingCallBack = savingCallBack;
    }

    /**
     * On withdraw button clicked.
     *
     * @param v the v
     */
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

    /**
     * On deposit button clicked.
     *
     * @param v the v
     */
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

package com.lunaticaliens.bankingapp.ui.transfer;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lunaticaliens.bankingapp.data.Repository;
import com.lunaticaliens.bankingapp.data.local.entities.Transactions;
import com.lunaticaliens.bankingapp.data.local.entities.User;
import com.lunaticaliens.bankingapp.utils.SessionManager;
import com.lunaticaliens.bankingapp.utils.Utils;

import java.util.concurrent.ExecutionException;

public class TransferViewModel extends AndroidViewModel {

    public String accountNumber = "";
    public String accountName = "";
    public String amount = "";
    private TransferCallBack transferCallBack;
    private final Repository repository;

    public TransferViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    void setTransferCallBack(TransferCallBack transferCallBack) {
        this.transferCallBack = transferCallBack;
    }

    public void onTransferButtonClick(View v) {
        v.setEnabled(false);

        if (accountNumber.isEmpty() || accountName.isEmpty() || amount.isEmpty()) {
            transferCallBack.onFailure(false, "Please fill all the fields to continue");
            v.setEnabled(true);
            return;
        }
        Double finalAmount = Double.parseDouble(amount);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            if (user.getBalance() >= finalAmount) {
                Transactions transactions = new Transactions(Utils.getCurrentDate(), finalAmount,
                        Utils.CREDIT, accountName, accountNumber);
                repository.insertTransaction(transactions);
                user.setBalance(user.getBalance() - finalAmount);
                repository.updateUser(user);

                amount = "";
                accountName = "";
                accountNumber = "";
                transferCallBack.onTransferSuccessful();
            } else {
                transferCallBack.onFailure(false, "Not enough balance");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            SessionManager.getInstance().expireToken();
            transferCallBack.onFailure(true, "User session expired, please login again");
        } finally {
            v.setEnabled(true);
        }

    }
}

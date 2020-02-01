package com.shimirokach.bankingapp.ui.transfer;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.concurrent.ExecutionException;

/**
 * The type Transfer view model.
 */
public class TransferViewModel extends AndroidViewModel {

    /**
     * The Account number.
     */
    public String accountNumber = "";
    /**
     * The Account name.
     */
    public String accountName = "";
    /**
     * The Amount.
     */
    public String amount = "";
    private TransferCallBack transferCallBack;
    private Repository repository;

    /**
     * Instantiates a new Transfer view model.
     *
     * @param application the application
     */
    public TransferViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Sets transfer call back.
     *
     * @param transferCallBack the transfer call back
     */
    void setTransferCallBack(TransferCallBack transferCallBack) {
        this.transferCallBack = transferCallBack;
    }

    /**
     * On transfer button click.
     *
     * @param v the v
     */
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

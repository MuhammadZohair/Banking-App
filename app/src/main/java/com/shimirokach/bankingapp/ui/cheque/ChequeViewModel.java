package com.shimirokach.bankingapp.ui.cheque;

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
 * The type Cheque view model.
 */
public class ChequeViewModel extends AndroidViewModel {

    /**
     * The Cheque number.
     */
    public String chequeNumber = "";
    /**
     * The Amount.
     */
    public String amount = "";
    private Repository repository;
    private ChequeCallBack chequeCallBack;

    /**
     * Instantiates a new Cheque view model.
     *
     * @param application the application
     */
    public ChequeViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Sets cheque call back.
     *
     * @param chequeCallBack the cheque call back
     */
    void setChequeCallBack(ChequeCallBack chequeCallBack) {
        this.chequeCallBack = chequeCallBack;
    }

    /**
     * On deposit button clicked.
     *
     * @param v the v
     */
    public void onDepositButtonClicked(View v) {
        v.setEnabled(false);

        if (chequeNumber.isEmpty() || amount.isEmpty()) {
            chequeCallBack.onFailure(false, "Please fill all the fields");
            return;
        }
        Double finalAmount = Double.parseDouble(amount);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            user.setBalance(user.getBalance() + finalAmount);
            repository.updateUser(user);

            Transactions transactions = new Transactions(Utils.getCurrentDate(),
                    finalAmount, Utils.DEBIT, "CHEQUE", chequeNumber);
            repository.insertTransaction(transactions);
            chequeCallBack.onDepositSuccessful();

            amount = "";
            chequeNumber = "";

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            SessionManager.getInstance().expireToken();
            chequeCallBack.onFailure(true, "User session expired, please login again");
        } finally {
            v.setEnabled(false);
        }
    }
}

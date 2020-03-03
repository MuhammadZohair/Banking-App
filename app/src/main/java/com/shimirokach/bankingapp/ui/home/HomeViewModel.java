package com.shimirokach.bankingapp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * The type Home view model.
 */
public class HomeViewModel extends AndroidViewModel {

    /**
     * The Balance.
     */
    public Double balance = 0D;
    /**
     * The Current date.
     */
    public String currentDate = "";

    private LiveData<List<Transactions>> allTransactions;

    /**
     * Instantiates a new Home view model.
     *
     * @param application the application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);

        Repository repository = new Repository(application);
        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            allTransactions = repository.getAllTransactions();

            balance = user.getBalance();
            currentDate = Utils.getCurrentDate();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
    LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }
}

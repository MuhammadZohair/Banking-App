package com.shimirokach.bankingapp.ui.home;

import android.app.Application;
import android.view.View;

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
     * The Full name.
     */
    public String fullName = "";
    /**
     * The Email address.
     */
    public String emailAddress = "";
    /**
     * The Balance.
     */
    public Double balance = 0D;
    /**
     * The Current date.
     */
    public String currentDate = "";
    private HomeCallBack homeCallBack;
    private Repository repository;
    private User user;

    private LiveData<List<Transactions>> allTransactions;

    /**
     * Instantiates a new Home view model.
     *
     * @param application the application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        try {
            user = repository.getUserByToken(SessionManager.getInstance().getToken());
            allTransactions = repository.getAllTransactions();

            fullName = user.getFullName();
            emailAddress = user.getEmail();
            balance = user.getBalance();
            currentDate = Utils.getCurrentDate();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets home call back.
     *
     * @param homeCallBack the home call back
     */
    void setHomeCallBack(HomeCallBack homeCallBack) {
        this.homeCallBack = homeCallBack;
    }

    /**
     * On edit clicked.
     *
     * @param view the view
     */
    public void onEditClicked(View view) {
        homeCallBack.onEditClicked(view);
    }

    /**
     * On logout clicked.
     *
     * @param view the view
     */
    public void onLogoutClicked(View view) {
        homeCallBack.onLogoutClicked(view);
    }

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
    public LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }
}

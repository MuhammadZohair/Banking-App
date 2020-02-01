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

public class HomeViewModel extends AndroidViewModel {

    public String fullName = "";
    public String emailAddress = "";
    public Double balance = 0D;
    public String currentDate = "";
    private HomeCallBack homeCallBack;
    private Repository repository;
    private User user;

    private LiveData<List<Transactions>> allTransactions;

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

    void setHomeCallBack(HomeCallBack homeCallBack) {
        this.homeCallBack = homeCallBack;
    }

    public void onEditClicked(View view) {
        homeCallBack.onEditClicked(view);
    }

    public void onLogoutClicked(View view) {
        homeCallBack.onLogoutClicked(view);
    }

    public LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }
}

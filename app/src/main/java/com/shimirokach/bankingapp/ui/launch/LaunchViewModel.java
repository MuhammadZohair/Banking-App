package com.shimirokach.bankingapp.ui.launch;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.concurrent.ExecutionException;

/**
 * The type Launch view model.
 */
public class LaunchViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";

    private LaunchPageCallBack launchPageCallBack = null;

    /**
     * The Email address.
     */
    public String emailAddress = "test@mail.com";
    /**
     * The Password.
     */
    public String password = "qwe123";
    /**
     * The Full name.
     */
    public String fullName = "Test User";
    private Repository repository;


    /**
     * Instantiates a new Launch view model.
     *
     * @param application the application
     */
    public LaunchViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Sets launch page call back.
     *
     * @param launchPageCallBack the launch page call back
     */
    void setLaunchPageCallBack(LaunchPageCallBack launchPageCallBack) {
        this.launchPageCallBack = launchPageCallBack;
    }

    /**
     * On start button pressed.
     *
     * @param view the view
     */
    public void onStartButtonPressed(View view) {
        try {
            User user = repository.loginUser(emailAddress, password);
            if (user != null) {
                user.setToken(Utils.generateToken());
                SessionManager.getInstance().setToken(user.getToken());
                repository.updateUser(user);
                launchPageCallBack.onLogin();
            } else {
                String token = Utils.generateToken();
                repository.insertUser(new User(emailAddress, password, fullName, 0.0, 0.0, token));
                SessionManager.getInstance().setToken(token);
                launchPageCallBack.onLogin();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

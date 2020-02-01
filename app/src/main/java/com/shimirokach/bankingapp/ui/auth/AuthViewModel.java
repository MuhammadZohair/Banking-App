package com.shimirokach.bankingapp.ui.auth;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.concurrent.ExecutionException;

public class AuthViewModel extends AndroidViewModel {

    private static final String TAG = "AuthViewModel";

    private Repository repository;
    private AuthCallBack authCallBack;

    public String emailAddress = "";
    public String password = "";
    public String confirmPassword = "";
    public String fullName = "";

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    void setAuthCallBack(AuthCallBack authCallBack) {
        this.authCallBack = authCallBack;
    }

    public void onLoginButtonClick(View view) {
        authCallBack.onStarted();

        if (emailAddress.isEmpty() || password.isEmpty()) {
            authCallBack.onFailure("Fields cannot be empty");
            return;
        }

        if (!Utils.validateEmail(emailAddress)) {
            authCallBack.onFailure("Invalid Email");
            return;
        }
        if (!Utils.validatePassword(password)) {
            authCallBack.onFailure("Invalid Password");
            return;
        }

        try {
            User user = repository.loginUser(emailAddress, password);
            if (user != null) {
                user.setToken(Utils.generateToken());
                SessionManager.getInstance().setToken(user.getToken());
                repository.updateUser(user);
                authCallBack.onSuccess();
            } else {
                authCallBack.onFailure("Incorrect email or password");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterButtonClick(View view) {

        authCallBack.onStarted();

        if (emailAddress.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty() || fullName.isEmpty()) {
            authCallBack.onFailure("Fields cannot be empty");
            return;
        }

        if (!Utils.validateEmail(emailAddress)) {
            authCallBack.onFailure("Invalid Email");
            return;
        }
        if (!Utils.validatePassword(password)) {
            authCallBack.onFailure("Invalid Password");
            return;
        }

        if (!password.equals(confirmPassword)) {
            authCallBack.onFailure("Password doesn't match");
            return;
        }

        try {
            if (repository.isUserPresent(emailAddress)) {
                authCallBack.onFailure("Email already exist");
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            authCallBack.onFailure("Error occurred, Please try again");
            return;
        }

        String token = Utils.generateToken();
        repository.insertUser(new User(emailAddress, password, fullName, 0.0, 0.0, token));
        SessionManager.getInstance().setToken(token);

        authCallBack.onSuccess();

    }

    public void onBackPressed() {
        authCallBack.onBackPressed();
    }
}

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

/**
 * The type Auth view model.
 */
public class AuthViewModel extends AndroidViewModel {

    private static final String TAG = "AuthViewModel";

    private Repository repository;
    private AuthCallBack authCallBack;

    /**
     * The Email address.
     */
    public String emailAddress = "";
    /**
     * The Password.
     */
    public String password = "";
    /**
     * The Confirm password.
     */
    public String confirmPassword = "";
    /**
     * The Full name.
     */
    public String fullName = "";

    /**
     * Instantiates a new Auth view model.
     *
     * @param application the application
     */
    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Sets auth call back.
     *
     * @param authCallBack the auth call back
     */
    void setAuthCallBack(AuthCallBack authCallBack) {
        this.authCallBack = authCallBack;
    }

    /**
     * On login button click.
     *
     * @param view the view
     */
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

    /**
     * On register button click.
     *
     * @param view the view
     */
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

    /**
     * On back pressed.
     */
    public void onBackPressed() {
        authCallBack.onBackPressed();
    }
}

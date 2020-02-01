package com.shimirokach.bankingapp.ui.profile;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;

import java.util.concurrent.ExecutionException;

/**
 * The type Profile view model.
 */
public class ProfileViewModel extends AndroidViewModel {

    /**
     * The Old password.
     */
    public String oldPassword = "";
    /**
     * The New password.
     */
    public String newPassword = "";
    /**
     * The Full name.
     */
    public String fullName = "";
    private Repository repository;
    private ProfileCallBack profileCallBack;

    /**
     * Instantiates a new Profile view model.
     *
     * @param application the application
     */
    public ProfileViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());
            fullName = user.getFullName();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets profile call back.
     *
     * @param profileCallBack the profile call back
     */
    void setProfileCallBack(ProfileCallBack profileCallBack) {
        this.profileCallBack = profileCallBack;
    }

    /**
     * On update button click.
     *
     * @param v the v
     */
    public void onUpdateButtonClick(View v) {
        v.setEnabled(false);

        if (oldPassword.isEmpty() || newPassword.isEmpty() || fullName.isEmpty()) {
            profileCallBack.onUpdateFailure(false, "Fields cannot be empty");
            v.setEnabled(true);
            return;
        }

        try {
            User user = repository.getUserByToken(SessionManager.getInstance().getToken());

            if (user.getPassword().equals(oldPassword)) {
                user.setFullName(fullName);
                user.setPassword(newPassword);
                repository.updateUser(user);
                profileCallBack.onUpdateSuccessful();
            } else {
                profileCallBack.onUpdateFailure(false, "Incorrect password");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            SessionManager.getInstance().expireToken();
            profileCallBack.onUpdateFailure(true, "User session expired, please login again");
        } finally {
            v.setEnabled(true);
        }

    }
}

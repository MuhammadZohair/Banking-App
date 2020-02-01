package com.shimirokach.bankingapp.ui.profile;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.data.Repository;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.SessionManager;

import java.util.concurrent.ExecutionException;

public class ProfileViewModel extends AndroidViewModel {

    public String oldPassword = "";
    public String newPassword = "";
    public String fullName = "";
    private Repository repository;
    private ProfileCallBack profileCallBack;

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

    void setProfileCallBack(ProfileCallBack profileCallBack) {
        this.profileCallBack = profileCallBack;
    }

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

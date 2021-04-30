package com.lunaticaliens.bankingapp.ui.main;

import android.app.Application;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;

@BindingMethods({
        @BindingMethod(
                type = BottomNavigationView.class,
                attribute = "app:onNavigationItemSelected",
                method = "setOnNavigationItemSelectedListener"
        ),
})

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    private MainCallBack mainCallBack;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    void setMainCallBack(MainCallBack mainCallBack) {
        this.mainCallBack = mainCallBack;
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {
        mainCallBack.onNavigationItemClick(item.getItemId());
        return true;
    }
}
package com.shimirokach.bankingapp.ui.home;

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

public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = "HomeViewModel";

    private HomeNavigator homeNavigator;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    void setHomeNavigator(HomeNavigator homeNavigator) {
        this.homeNavigator = homeNavigator;
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {
        homeNavigator.onNavigationItemClick(item.getItemId());
        return true;
    }
}
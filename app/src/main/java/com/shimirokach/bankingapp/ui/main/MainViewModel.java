package com.shimirokach.bankingapp.ui.main;

import android.app.Application;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The type Main view model.
 */
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

    /**
     * Instantiates a new Main view model.
     *
     * @param application the application
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Sets main call back.
     *
     * @param mainCallBack the main call back
     */
    void setMainCallBack(MainCallBack mainCallBack) {
        this.mainCallBack = mainCallBack;
    }

    /**
     * On navigation click boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean onNavigationClick(@NonNull MenuItem item) {
        mainCallBack.onNavigationItemClick(item.getItemId());
        return true;
    }
}
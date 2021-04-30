package com.lunaticaliens.bankingapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lunaticaliens.bankingapp.data.local.dao.TransactionsDao;
import com.lunaticaliens.bankingapp.data.local.dao.UserDao;
import com.lunaticaliens.bankingapp.data.local.entities.Transactions;
import com.lunaticaliens.bankingapp.data.local.entities.User;

@Database(entities = {User.class, Transactions.class}, version = 1)
public abstract class BankDatabase extends RoomDatabase {

    private static BankDatabase instance;

    public static synchronized BankDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BankDatabase.class, "bank_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract TransactionsDao transactionsDao();
}

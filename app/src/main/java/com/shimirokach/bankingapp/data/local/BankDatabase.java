package com.shimirokach.bankingapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shimirokach.bankingapp.data.local.dao.TransactionsDao;
import com.shimirokach.bankingapp.data.local.dao.UserDao;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.data.local.entities.User;

/**
 * The type Bank database.
 */
@Database(entities = {User.class, Transactions.class}, version = 1)
public abstract class BankDatabase extends RoomDatabase {

    private static BankDatabase instance;

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static synchronized BankDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BankDatabase.class, "bank_app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    /**
     * User dao user dao.
     *
     * @return the user dao
     */
    public abstract UserDao userDao();

    /**
     * Transactions dao transactions dao.
     *
     * @return the transactions dao
     */
    public abstract TransactionsDao transactionsDao();
}

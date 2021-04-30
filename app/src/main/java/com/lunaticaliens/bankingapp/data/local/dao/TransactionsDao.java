package com.lunaticaliens.bankingapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lunaticaliens.bankingapp.data.local.entities.Transactions;

import java.util.List;

@Dao
public interface TransactionsDao {

    @Insert
    void insertTransaction(Transactions transaction);

    @Update
    void updateTransaction(Transactions transaction);

    @Delete
    void deleteTransactions(Transactions user);

    @Query("DELETE FROM transactions_table")
    void deleteAllTransactions();

    @Query("SELECT * FROM transactions_table ORDER BY transaction_id ASC")
    LiveData<List<Transactions>> getAllTransactions();
}

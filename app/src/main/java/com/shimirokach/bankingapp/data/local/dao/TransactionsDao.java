package com.shimirokach.bankingapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shimirokach.bankingapp.data.local.entities.Transactions;

import java.util.List;

/**
 * The interface Transactions dao.
 */
@Dao
public interface TransactionsDao {

    /**
     * Insert transaction.
     *
     * @param transaction the transaction
     */
    @Insert
    void insertTransaction(Transactions transaction);

    /**
     * Update transaction.
     *
     * @param transaction the transaction
     */
    @Update
    void updateTransaction(Transactions transaction);

    /**
     * Delete transactions.
     *
     * @param user the user
     */
    @Delete
    void deleteTransactions(Transactions user);

    /**
     * Delete all transactions.
     */
    @Query("DELETE FROM transactions_table")
    void deleteAllTransactions();

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
    @Query("SELECT * FROM transactions_table ORDER BY transaction_id ASC")
    LiveData<List<Transactions>> getAllTransactions();
}

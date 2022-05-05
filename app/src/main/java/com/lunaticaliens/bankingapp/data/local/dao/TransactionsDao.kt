package com.lunaticaliens.bankingapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lunaticaliens.bankingapp.data.local.entities.Transactions

@Dao
interface TransactionsDao {
    @Insert
    fun insertTransaction(transaction: Transactions?)

    @Update
    fun updateTransaction(transaction: Transactions?)

    @Delete
    fun deleteTransactions(user: Transactions?)

    @Query("DELETE FROM transactions_table")
    fun deleteAllTransactions()

    @get:Query("SELECT * FROM transactions_table ORDER BY transaction_id ASC")
    val allTransactions: LiveData<List<Transactions?>?>?
}
package com.lunaticaliens.bankingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lunaticaliens.bankingapp.data.local.BankDatabase
import com.lunaticaliens.bankingapp.data.local.dao.TransactionsDao
import com.lunaticaliens.bankingapp.data.local.dao.UserDao
import com.lunaticaliens.bankingapp.data.local.entities.Transactions
import com.lunaticaliens.bankingapp.data.local.entities.User

@Database(entities = [User::class, Transactions::class], version = 1)
abstract class BankDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?
    abstract fun transactionsDao(): TransactionsDao?

    companion object {
        private var instance: BankDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): BankDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BankDatabase::class.java, "bank_app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
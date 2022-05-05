package com.lunaticaliens.bankingapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.lunaticaliens.bankingapp.data.local.BankDatabase.Companion.getInstance
import com.lunaticaliens.bankingapp.data.local.dao.TransactionsDao
import com.lunaticaliens.bankingapp.data.local.dao.UserDao
import com.lunaticaliens.bankingapp.data.local.entities.Transactions
import com.lunaticaliens.bankingapp.data.local.entities.User
import com.lunaticaliens.bankingapp.utils.Utils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

@DelicateCoroutinesApi
class Repository(application: Application?) {
    private val userDao: UserDao?
    private val transactionsDao: TransactionsDao?

    val allUsers: LiveData<List<User?>?>?
    val allTransactions: LiveData<List<Transactions?>?>?

    fun insertUser(user: User?) {
        GlobalScope.launch(Dispatchers.IO) {
            userDao?.insert(user)
        }
    }

    fun updateUser(user: User?) {
        GlobalScope.launch(Dispatchers.IO) {
            userDao?.update(user)
        }
    }

    fun deleteUser(user: User?) {
        GlobalScope.launch(Dispatchers.IO) {
            userDao?.delete(user)
        }
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun loginUser(email: String?, password: String?): User {
        return UserLoginAsyncTask(userDao).execute(email, password).get()!!
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun isUserPresent(email: String?): Boolean {
        return UserPresentAsyncTask(userDao).execute(email).get()
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getUserById(id: Long?): User {
        return UserGetByIdAsyncTask(userDao).execute(id).get()!!
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getUserByToken(token: String?): User {
        return UserGetByTokenAsyncTask(userDao).execute(token).get()!!
    }

    fun insertTransaction(transaction: Transactions?) {
        TransactionAsyncTask(transactionsDao, Utils.INSERT).execute(transaction)
    }

    fun updateTransaction(transaction: Transactions?) {
        TransactionAsyncTask(transactionsDao, Utils.UPDATE).execute(transaction)
    }

    fun deleteTransaction(transaction: Transactions?) {
        TransactionAsyncTask(transactionsDao, Utils.DELETE).execute(transaction)
    }


    private class UserLoginAsyncTask(private val userDao: UserDao?) :
        AsyncTask<String?, Void?, User?>() {
        protected override fun doInBackground(vararg strings: String): User? {
            return userDao!!.login(strings[0], strings[1])
        }
    }

//    private class UserGetByIdAsyncTask(private val userDao: UserDao?) :
//        AsyncTask<Long?, Void?, User?>() {
//        protected override fun doInBackground(vararg longs: Long): User? {
//            return userDao!!.getById(longs[0])
//        }
//    }
//
//    private class UserGetByTokenAsyncTask(private val userDao: UserDao?) :
//        AsyncTask<String?, Void?, User?>() {
//        protected override fun doInBackground(vararg strings: String): User? {
//            return userDao!!.getByToken(strings[0])
//        }
//    }
//
//    private class UserPresentAsyncTask(private val userDao: UserDao?) :
//        AsyncTask<String?, Void?, Boolean>() {
//        protected override fun doInBackground(vararg strings: String): Boolean {
//            return userDao!!.isRegistered(strings[0]) != null
//        }
//    }
//
//    private class TransactionAsyncTask(
//        private val transactionsDao: TransactionsDao?,
//        private val code: Int
//    ) : AsyncTask<Transactions?, Void?, Void?>() {
//        protected override fun doInBackground(vararg transactions: Transactions): Void? {
//            when (code) {
//                Utils.INSERT -> transactionsDao!!.insertTransaction(
//                    transactions[0]
//                )
//                Utils.UPDATE -> transactionsDao!!.updateTransaction(
//                    transactions[0]
//                )
//                Utils.DELETE -> transactionsDao!!.deleteTransactions(
//                    transactions[0]
//                )
//                Utils.DELETE_ALL -> transactionsDao!!.deleteAllTransactions()
//            }
//            return null
//        }
//    }

    init {
        val database = getInstance(application!!)
        userDao = database!!.userDao()
        transactionsDao = database.transactionsDao()
        allUsers = userDao!!.all
        allTransactions = transactionsDao!!.allTransactions
    }
}
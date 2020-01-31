package com.shimirokach.bankingapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.shimirokach.bankingapp.data.local.BankDatabase;
import com.shimirokach.bankingapp.data.local.dao.TransactionsDao;
import com.shimirokach.bankingapp.data.local.dao.UserDao;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.data.local.entities.User;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {

    private UserDao userDao;
    private TransactionsDao transactionsDao;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Transactions>> allTransactions;

    public Repository(Application application) {
        BankDatabase database = BankDatabase.getInstance(application);
        userDao = database.userDao();
        transactionsDao = database.transactionsDao();

        allUsers = userDao.getAllUsers();
        allTransactions = transactionsDao.getAllTransactions();
    }

    public void insertUser(User user) {
        new UserAsyncTask(userDao, Utils.INSERT).execute(user);
    }

    public void updateUser(User user) {
        new UserAsyncTask(userDao, Utils.UPDATE).execute(user);
    }

    public void deleteUser(User user) {
        new UserAsyncTask(userDao, Utils.DELETE).execute(user);
    }

    public User loginUser(String email, String password) throws ExecutionException, InterruptedException {
        return new UserLoginAsyncTask(userDao).execute(email, password).get();
    }

    public Boolean isUserPresent(String email) throws ExecutionException, InterruptedException {
        return new UserPresentAsyncTask(userDao).execute(email).get();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }


    public void insertTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.INSERT).execute(transaction);
    }

    public void updateTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.UPDATE).execute(transaction);
    }

    public void deleteTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.DELETE).execute(transaction);
    }

    public LiveData<List<Transactions>> getAllTransactions() {
        return allTransactions;
    }


    private static class UserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private Integer code;

        private UserAsyncTask(UserDao userDao, Integer code) {
            this.userDao = userDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(User... users) {
            switch (code) {
                case Utils.INSERT:
                    userDao.insertUser(users[0]);
                    break;
                case Utils.UPDATE:
                    userDao.updateUser(users[0]);
                    break;
                case Utils.DELETE:
                    userDao.deleteUser(users[0]);
                    break;
                case Utils.DELETE_ALL:
                    userDao.deleteAllUsers();
                    break;

            }
            return null;
        }
    }

    private static class UserLoginAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        private UserLoginAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.loginUser(strings[0], strings[1]);
        }
    }

    private static class UserPresentAsyncTask extends AsyncTask<String, Void, Boolean> {
        private UserDao userDao;

        private UserPresentAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return userDao.isUserRegistered(strings[0])!= null;
        }
    }

    private static class TransactionAsyncTask extends AsyncTask<Transactions, Void, Void> {
        private TransactionsDao transactionsDao;
        private Integer code;

        private TransactionAsyncTask(TransactionsDao transactionsDao, Integer code) {
            this.transactionsDao = transactionsDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            switch (code) {
                case Utils.INSERT:
                    transactionsDao.insertTransaction(transactions[0]);
                    break;
                case Utils.UPDATE:
                    transactionsDao.updateTransaction(transactions[0]);
                    break;
                case Utils.DELETE:
                    transactionsDao.deleteTransactions(transactions[0]);
                    break;
                case Utils.DELETE_ALL:
                    transactionsDao.deleteAllTransactions();
            }
            return null;
        }
    }
}

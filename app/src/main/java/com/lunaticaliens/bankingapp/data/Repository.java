package com.lunaticaliens.bankingapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lunaticaliens.bankingapp.data.local.BankDatabase;
import com.lunaticaliens.bankingapp.data.local.dao.TransactionsDao;
import com.lunaticaliens.bankingapp.data.local.dao.UserDao;
import com.lunaticaliens.bankingapp.data.local.entities.Transactions;
import com.lunaticaliens.bankingapp.data.local.entities.User;
import com.lunaticaliens.bankingapp.utils.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {

    private final UserDao userDao;
    private final TransactionsDao transactionsDao;

    private final LiveData<List<User>> allUsers;
    private final LiveData<List<Transactions>> allTransactions;

    public Repository(Application application) {
        BankDatabase database = BankDatabase.getInstance(application);
        userDao = database.userDao();
        transactionsDao = database.transactionsDao();

        allUsers = userDao.getAll();
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

    public User getUserById(Long id) throws ExecutionException, InterruptedException {
        return new UserGetByIdAsyncTask(userDao).execute(id).get();
    }

    public User getUserByToken(String token) throws ExecutionException, InterruptedException {
        return new UserGetByTokenAsyncTask(userDao).execute(token).get();
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
        private final UserDao userDao;
        private final Integer code;

        private UserAsyncTask(UserDao userDao, Integer code) {
            this.userDao = userDao;
            this.code = code;
        }

        @Override
        protected Void doInBackground(User... users) {
            switch (code) {
                case Utils.INSERT:
                    userDao.insert(users[0]);
                    break;
                case Utils.UPDATE:
                    userDao.update(users[0]);
                    break;
                case Utils.DELETE:
                    userDao.delete(users[0]);
                    break;
                case Utils.DELETE_ALL:
                    userDao.deleteAll();
                    break;

            }
            return null;
        }
    }

    private static class UserLoginAsyncTask extends AsyncTask<String, Void, User> {
        private final UserDao userDao;

        private UserLoginAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.login(strings[0], strings[1]);
        }
    }

    private static class UserGetByIdAsyncTask extends AsyncTask<Long, Void, User> {
        private final UserDao userDao;

        private UserGetByIdAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(Long... longs) {
            return userDao.getById(longs[0]);
        }
    }

    private static class UserGetByTokenAsyncTask extends AsyncTask<String, Void, User> {
        private final UserDao userDao;

        private UserGetByTokenAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.getByToken(strings[0]);
        }
    }

    private static class UserPresentAsyncTask extends AsyncTask<String, Void, Boolean> {
        private final UserDao userDao;

        private UserPresentAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return userDao.isRegistered(strings[0]) != null;
        }
    }

    private static class TransactionAsyncTask extends AsyncTask<Transactions, Void, Void> {
        private final TransactionsDao transactionsDao;
        private final Integer code;

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

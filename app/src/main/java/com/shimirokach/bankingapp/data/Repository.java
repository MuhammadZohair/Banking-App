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

/**
 * The type Repository.
 */
public class Repository {

    private UserDao userDao;
    private TransactionsDao transactionsDao;

    private LiveData<List<Transactions>> allTransactions;

    /**
     * Instantiates a new Repository.
     *
     * @param application the application
     */
    public Repository(Application application) {
        BankDatabase database = BankDatabase.getInstance(application);
        userDao = database.userDao();
        transactionsDao = database.transactionsDao();

        LiveData<List<User>> allUsers = userDao.getAll();
        allTransactions = transactionsDao.getAllTransactions();
    }

    /**
     * Insert user.
     *
     * @param user the user
     */
    public void insertUser(User user) {
        new UserAsyncTask(userDao, Utils.INSERT).execute(user);
    }

    /**
     * Update user.
     *
     * @param user the user
     */
    public void updateUser(User user) {
        new UserAsyncTask(userDao, Utils.UPDATE).execute(user);
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public User getUserById(Long id) throws ExecutionException, InterruptedException {
        return new UserGetByIdAsyncTask(userDao).execute(id).get();
    }

    /**
     * Gets user by token.
     *
     * @param token the token
     * @return the user by token
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public User getUserByToken(String token) throws ExecutionException, InterruptedException {
        return new UserGetByTokenAsyncTask(userDao).execute(token).get();
    }

    /**
     * Insert transaction.
     *
     * @param transaction the transaction
     */
    public void insertTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.INSERT).execute(transaction);
    }

    /**
     * Gets all transactions.
     *
     * @return the all transactions
     */
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

    private static class UserGetByIdAsyncTask extends AsyncTask<Long, Void, User> {
        private UserDao userDao;

        private UserGetByIdAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(Long... longs) {
            return userDao.getById(longs[0]);
        }
    }

    private static class UserGetByTokenAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        private UserGetByTokenAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.getByToken(strings[0]);
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

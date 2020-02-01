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

    private LiveData<List<User>> allUsers;
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

        allUsers = userDao.getAll();
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
     * Delete user.
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        new UserAsyncTask(userDao, Utils.DELETE).execute(user);
    }

    /**
     * Login user user.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public User loginUser(String email, String password) throws ExecutionException, InterruptedException {
        return new UserLoginAsyncTask(userDao).execute(email, password).get();
    }

    /**
     * Is user present boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public Boolean isUserPresent(String email) throws ExecutionException, InterruptedException {
        return new UserPresentAsyncTask(userDao).execute(email).get();
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
     * Gets all users.
     *
     * @return the all users
     */
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
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
     * Update transaction.
     *
     * @param transaction the transaction
     */
    public void updateTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.UPDATE).execute(transaction);
    }

    /**
     * Delete transaction.
     *
     * @param transaction the transaction
     */
    public void deleteTransaction(Transactions transaction) {
        new TransactionAsyncTask(transactionsDao, Utils.DELETE).execute(transaction);
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

    private static class UserLoginAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        private UserLoginAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.login(strings[0], strings[1]);
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

    private static class UserPresentAsyncTask extends AsyncTask<String, Void, Boolean> {
        private UserDao userDao;

        private UserPresentAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return userDao.isRegistered(strings[0]) != null;
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

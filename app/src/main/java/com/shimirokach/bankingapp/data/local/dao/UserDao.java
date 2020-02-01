package com.shimirokach.bankingapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shimirokach.bankingapp.data.local.entities.User;

import java.util.List;

/**
 * The interface User dao.
 */
@Dao
public interface UserDao {

    /**
     * Insert.
     *
     * @param user the user
     */
    @Insert
    void insert(User user);

    /**
     * Update.
     *
     * @param user the user
     */
    @Update
    void update(User user);

    /**
     * Delete.
     *
     * @param user the user
     */
    @Delete
    void delete(User user);

    /**
     * Delete all.
     */
    @Query("DELETE FROM users_table")
    void deleteAll();

    /**
     * Gets all.
     *
     * @return the all
     */
    @Query("SELECT * FROM users_table ORDER BY user_id ASC")
    LiveData<List<User>> getAll();

    /**
     * Login user.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     */
    @Query("SELECT * FROM users_table WHERE email_address = :email AND password = :password")
    User login(String email, String password);

    /**
     * Is registered user.
     *
     * @param email the email
     * @return the user
     */
    @Query("SELECT * FROM users_table WHERE email_address = :email")
    User isRegistered(String email);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Query("SELECT * FROM users_table WHERE user_id = :id")
    User getById(Long id);

    /**
     * Gets by token.
     *
     * @param string the string
     * @return the by token
     */
    @Query("SELECT * FROM users_table WHERE token = :string")
    User getByToken(String string);
}

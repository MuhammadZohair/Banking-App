package com.shimirokach.bankingapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shimirokach.bankingapp.data.local.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM users_table")
    void deleteAllUsers();

    @Query("SELECT * FROM users_table ORDER BY user_id ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users_table WHERE email_address = :email AND password = :password")
    User loginUser(String email, String password);

    @Query("SELECT * FROM users_table WHERE email_address = :email")
    User isUserRegistered(String email);
}

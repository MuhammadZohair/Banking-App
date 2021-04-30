package com.lunaticaliens.bankingapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lunaticaliens.bankingapp.data.local.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users_table")
    void deleteAll();

    @Query("SELECT * FROM users_table ORDER BY user_id ASC")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users_table WHERE email_address = :email AND password = :password")
    User login(String email, String password);

    @Query("SELECT * FROM users_table WHERE email_address = :email")
    User isRegistered(String email);

    @Query("SELECT * FROM users_table WHERE user_id = :id")
    User getById(Long id);

    @Query("SELECT * FROM users_table WHERE token = :string")
    User getByToken(String string);
}

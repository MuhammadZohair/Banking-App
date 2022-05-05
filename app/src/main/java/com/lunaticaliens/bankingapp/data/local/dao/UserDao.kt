package com.lunaticaliens.bankingapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lunaticaliens.bankingapp.data.local.entities.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)

    @Query("DELETE FROM users_table")
    fun deleteAll()

    @get:Query("SELECT * FROM users_table ORDER BY user_id ASC")
    val all: LiveData<List<User?>?>?

    @Query("SELECT * FROM users_table WHERE email_address = :email AND password = :password")
    fun login(email: String?, password: String?): User?

    @Query("SELECT * FROM users_table WHERE email_address = :email")
    fun isRegistered(email: String?): User?

    @Query("SELECT * FROM users_table WHERE user_id = :id")
    fun getById(id: Long?): User?

    @Query("SELECT * FROM users_table WHERE token = :string")
    fun getByToken(string: String?): User?
}
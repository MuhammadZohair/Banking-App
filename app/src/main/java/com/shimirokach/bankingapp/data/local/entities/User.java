package com.shimirokach.bankingapp.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type User.
 */
@Entity(tableName = "users_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private Long userId;

    @ColumnInfo(name = "email_address")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "balance")
    private Double balance;

    @ColumnInfo(name = "savings")
    private Double savings;

    @ColumnInfo(name = "token")
    private String token;

    /**
     * Instantiates a new User.
     *
     * @param email    the email
     * @param password the password
     * @param fullName the full name
     * @param balance  the balance
     * @param savings  the savings
     * @param token    the token
     */
    public User(String email, String password, String fullName, Double balance, Double savings, String token) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.balance = balance;
        this.savings = savings;
        this.token = token;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Gets savings.
     *
     * @return the savings
     */
    public Double getSavings() {
        return savings;
    }

    /**
     * Sets savings.
     *
     * @param savings the savings
     */
    public void setSavings(Double savings) {
        this.savings = savings;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", savings=" + savings +
                ", token='" + token + '\'' +
                '}';
    }
}

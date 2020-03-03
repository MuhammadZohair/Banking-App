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
    @ColumnInfo(name = "balance")
    private Double balance;

    @ColumnInfo(name = "savings")
    private Double savings;

    @ColumnInfo(name = "token")
    private String token;

    /**
     * Instantiates a new User.
     *
     * @param balance the balance
     * @param savings the savings
     * @param token   the token
     */
    public User(Double balance, Double savings, String token) {
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
                ", balance=" + balance +
                ", savings=" + savings +
                ", token='" + token + '\'' +
                '}';
    }
}

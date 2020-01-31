package com.shimirokach.bankingapp.data.local.entities;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions_table")
public class Transactions {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    private Long transactionId;

    private String date;
    private Double amount;
    private Integer type;

    @ColumnInfo(name = "receiver_id")
    private String receiverId;

    public Transactions(Long transactionId, String date, Double amount, Integer type, String receiverId) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.receiverId = receiverId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", receiverId='" + receiverId + '\'' +
                '}';
    }
}

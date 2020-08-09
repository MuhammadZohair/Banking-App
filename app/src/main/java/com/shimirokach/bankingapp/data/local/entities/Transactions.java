package com.shimirokach.bankingapp.data.local.entities;

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

    @ColumnInfo(name = "receiver_name")
    private String receiverName;

    @ColumnInfo(name = "receiver_account")
    private String receiverAccount;

    public Transactions(String date, Double amount, Integer type, String receiverName, String receiverAccount) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.receiverName = receiverName;
        this.receiverAccount = receiverAccount;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAccount='" + receiverAccount + '\'' +
                '}';
    }
}

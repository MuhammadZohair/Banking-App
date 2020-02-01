package com.shimirokach.bankingapp.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Transactions.
 */
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

    /**
     * Instantiates a new Transactions.
     *
     * @param date            the date
     * @param amount          the amount
     * @param type            the type
     * @param receiverName    the receiver name
     * @param receiverAccount the receiver account
     */
    public Transactions(String date, Double amount, Integer type, String receiverName, String receiverAccount) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.receiverName = receiverName;
        this.receiverAccount = receiverAccount;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets transaction id.
     *
     * @param transactionId the transaction id
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Gets receiver name.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Sets receiver name.
     *
     * @param receiverName the receiver name
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * Gets receiver account.
     *
     * @return the receiver account
     */
    public String getReceiverAccount() {
        return receiverAccount;
    }

    /**
     * Sets receiver account.
     *
     * @param receiverAccount the receiver account
     */
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

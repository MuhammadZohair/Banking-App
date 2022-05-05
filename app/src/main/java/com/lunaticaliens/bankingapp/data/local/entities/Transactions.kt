package com.lunaticaliens.bankingapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
class Transactions(
    var date: String,
    var amount: Double,
    var type: Int,
    @field:ColumnInfo(
        name = "receiver_name"
    ) var receiverName: String,
    @field:ColumnInfo(name = "receiver_account") var receiverAccount: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    var transactionId: Long? = null

    override fun toString(): String {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAccount='" + receiverAccount + '\'' +
                '}'
    }
}
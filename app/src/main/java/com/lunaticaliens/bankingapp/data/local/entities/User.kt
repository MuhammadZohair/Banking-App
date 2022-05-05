package com.lunaticaliens.bankingapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
class User(
    @field:ColumnInfo(name = "email_address") var email: String,
    @field:ColumnInfo(
        name = "password"
    ) var password: String,
    @field:ColumnInfo(name = "full_name") var fullName: String,
    @field:ColumnInfo(
        name = "balance"
    ) var balance: Double,
    @field:ColumnInfo(name = "savings") var savings: Double,
    @field:ColumnInfo(
        name = "token"
    ) var token: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long? = null

    override fun toString(): String {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", savings=" + savings +
                ", token='" + token + '\'' +
                '}'
    }
}
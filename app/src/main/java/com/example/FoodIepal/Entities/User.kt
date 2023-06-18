package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["username"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val userID: Int?,

    @ColumnInfo
    val username: String,

    @ColumnInfo
    val password: String
)
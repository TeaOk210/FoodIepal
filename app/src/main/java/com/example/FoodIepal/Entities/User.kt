package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "username")
    @PrimaryKey(autoGenerate = false)
    val username: String,

    @ColumnInfo(name = "password")
    val password: String
)
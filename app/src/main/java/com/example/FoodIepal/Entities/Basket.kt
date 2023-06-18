package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Basket(
    @PrimaryKey
    @ColumnInfo
    val name: String,

    @ColumnInfo
    val dose: String
)
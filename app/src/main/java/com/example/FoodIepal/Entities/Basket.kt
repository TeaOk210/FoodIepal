package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Basket",
    indices = [Index("username")],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["username"],
            childColumns = ["username"]
        )
    ]
)
data class Basket(
    @PrimaryKey
    @ColumnInfo
    val name: String,

    @ColumnInfo
    val dose: String,

    @ColumnInfo
    val username: String
)
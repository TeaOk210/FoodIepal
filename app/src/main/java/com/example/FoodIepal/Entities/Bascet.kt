package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Basсet",
    indices = [Index("username")],
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["username"],
            childColumns = ["username"]
        )
    ]
)
data class Bascet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "dose") val dose: String,
    @ColumnInfo(name = "username") val User: String
)

package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Recipes",
    indices = [Index("username")],
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["username"],
            childColumns = ["username"]
        )
    ]
)
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipe_name") val recipeName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "preparation") val preparation: String,
    @ColumnInfo(name = "items") val recipeItems: String,
    @ColumnInfo(name = "calories") val calories: Int,
    @ColumnInfo(name = "cook_time") val cookTime: Int,
    @ColumnInfo(name = "image_path") val imagePath: ByteArray,
    @ColumnInfo(name = "on_favorite") val onFavorite: Boolean,
    @ColumnInfo(name = "username") val User: String,
    @ColumnInfo(name = "recipe_type") val recipeType: RecipeType
)

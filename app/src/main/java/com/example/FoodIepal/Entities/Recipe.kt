package com.example.FoodIepal.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Recipe",
    indices = [Index("username")],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["username"],
            childColumns = ["username"]
        )
    ]
)
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "recipe_name") val recipeName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "preparation") val preparation: String,
    @ColumnInfo(name = "items") val recipeItems: String,
    @ColumnInfo(name = "calories") val calories: Int,
    @ColumnInfo(name = "cook_time") val cookTime: Int,
    @ColumnInfo(name = "image_path") val imagePath: ByteArray,
    @ColumnInfo(name = "recipe_type") val recipeType: RecipeType,
    @ColumnInfo(name = "username") val User: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false
        if (recipeName != other.recipeName) return false
        if (description != other.description) return false
        if (preparation != other.preparation) return false
        if (recipeItems != other.recipeItems) return false
        if (calories != other.calories) return false
        if (cookTime != other.cookTime) return false
        if (!imagePath.contentEquals(other.imagePath)) return false
        if (User != other.User) return false
        if (recipeType != other.recipeType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result!! + recipeName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + preparation.hashCode()
        result = 31 * result + recipeItems.hashCode()
        result = 31 * result + calories
        result = 31 * result + cookTime
        result = 31 * result + imagePath.contentHashCode()
        result = 31 * result + User.hashCode()
        result = 31 * result + recipeType.hashCode()
        return result
    }
}

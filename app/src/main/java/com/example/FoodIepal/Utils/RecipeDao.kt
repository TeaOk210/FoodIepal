package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.Entities.RecipeType

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM Recipe WHERE username = :username OR recipe_type = 'DEFAULT'")
    fun getAllRecipesForUser(username: String): LiveData<List<Recipe>>

    @Query("UPDATE Recipe SET recipe_type = :type WHERE recipe_name = :name")
    suspend fun updateRecipeType(name: String, type: RecipeType)

    @Query("SELECT * FROM recipe WHERE recipe_type IN (:types)")
    fun getRecipesByType(vararg types: RecipeType): LiveData<List<Recipe>>

    @Query("SELECT recipe_type FROM Recipe WHERE recipe_name = :recipeName AND username = :user")
    suspend fun getRecipeType(recipeName: String, user: String): RecipeType?
}
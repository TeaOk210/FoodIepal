package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.Entities.RecipeType

class RecipeRepository(private val dao: RecipeDao) {

    fun insertRecipe(recipe: Recipe) = dao.insertRecipe(recipe)

    fun getAllRecipes(user: String): LiveData<List<Recipe>> = dao.getAllRecipesForUser(user)

    suspend fun updateRecipeType(name: String, type: RecipeType) = dao.updateRecipeType(name, type)

    fun getRecipesByType(vararg types: RecipeType): LiveData<List<Recipe>> =
        dao.getRecipesByType(
            *types
        )

    suspend fun getRecipeType(name: String, user: String): RecipeType? = dao.getRecipeType(name, user)
}
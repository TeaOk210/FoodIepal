package com.example.FoodIepal.VIew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.Entities.RecipeType
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.Utils.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(
    application: Application
): AndroidViewModel(application) {

    val allRecipes: LiveData<List<Recipe>>
    private val repository: RecipeRepository
    private val sessionManager: SessionManager

    init {
        val dao = DataBase.getDatabase(application).getRecipeDao()
        repository = RecipeRepository(dao)
        sessionManager = SessionManager(application)
        allRecipes = repository.getAllRecipes(sessionManager.getUserName())
    }

    fun updateRecipeType(name: String, type: RecipeType) =
        viewModelScope.launch(Dispatchers.IO) {repository.updateRecipeType(name, type)}

    fun insertRecipe(recipe: Recipe) =
        viewModelScope.launch(Dispatchers.IO) {repository.insertRecipe(recipe)}
}
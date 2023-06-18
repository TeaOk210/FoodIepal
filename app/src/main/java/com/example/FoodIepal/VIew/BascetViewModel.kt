package com.example.FoodIepal.VIew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.BasketRepository
import com.example.FoodIepal.Utils.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BascetViewModel(
    application: Application
): AndroidViewModel(application) {

    val allBascet: LiveData<List<Basket>>
    private val repository: BasketRepository
    private val sessionManager: SessionManager

    init {
        val dao = DataBase.getDatabase(application).getBascetDao()
        repository = BasketRepository(dao)
        sessionManager = SessionManager(application)
        allBascet = repository.getAllbasket(sessionManager.getUserName())
    }

    fun insertBascet(bascet: Basket) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertBasket(bascet) }

    fun deleteBascet(name: String) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteBasket(name) }
}
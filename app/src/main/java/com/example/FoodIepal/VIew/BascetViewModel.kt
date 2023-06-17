package com.example.FoodIepal.VIew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.Utils.BascetRepository
import com.example.FoodIepal.Utils.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BascetViewModel(
    application: Application
): AndroidViewModel (application) {

    val allBascet: LiveData<List<Basket>>
    private val repository: BascetRepository

    init {
        val dao = DataBase.getDatabase(application).getBascetDao()
        repository = BascetRepository(dao)
        allBascet = repository.getAllBascet()
    }

    fun insertBascet(bascet: Basket) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertBascet(bascet) }

    fun deleteBascet(name: String) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteBascetByName(name) }
}
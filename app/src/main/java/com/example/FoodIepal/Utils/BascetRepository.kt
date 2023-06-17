package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import com.example.FoodIepal.Entities.Basket

class BascetRepository(private val dao: BascetDao) {

    fun getAllBascet(): LiveData<List<Basket>> = dao.getAllBascet()

    suspend fun insertBascet(bascet: Basket) = dao.insertBascet(bascet)

    suspend fun deleteBascetByName(name: String) = dao.deleteBascetByName(name)
}
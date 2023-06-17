package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import com.example.FoodIepal.Entities.Bascet

class BascetRepository(private val dao: BascetDao) {

    fun getAllBascet(): LiveData<List<Bascet>> = dao.getAllBascet()

    suspend fun insertBascet(bascet: Bascet) {
        dao.insertBascet(bascet)
    }

    suspend fun deleteBascetByName(name: String) {
        dao.deleteBascetByName(name)
    }
}
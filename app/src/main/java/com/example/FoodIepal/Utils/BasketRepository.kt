package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import com.example.FoodIepal.Entities.Basket

class BasketRepository(
    private val dao: BasketDao
) {
    suspend fun insertBasket(item: Basket) = dao.insertBasket(item)

    fun getAllbasket(): LiveData<List<Basket>> = dao.getAllBasket()

    suspend fun deleteBasket(name: String) = dao.deleteBasket(name)
}
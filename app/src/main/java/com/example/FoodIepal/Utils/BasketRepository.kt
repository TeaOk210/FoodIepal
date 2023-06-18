package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import com.example.FoodIepal.Entities.Basket

class BasketRepository(
    private val dao: BasketDao
) {
    suspend fun insertBasket(item: Basket) = dao.insertBasket(item)

    fun getAllbasket(user: String): LiveData<List<Basket>> = dao.getAllBasket(user)

    suspend fun deleteBasket(name: String) = dao.deleteBasket(name)
}
package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.FoodIepal.Entities.Basket

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBasket(item: Basket)

    @Query("SELECT * FROM Basket WHERE username = :username")
    fun getAllBasket(username: String): LiveData<List<Basket>>

    @Query("DELETE FROM Basket WHERE name = :name")
    suspend fun deleteBasket(name: String)
}
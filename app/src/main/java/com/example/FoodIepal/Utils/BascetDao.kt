package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.FoodIepal.Entities.Basket

@Dao
interface BascetDao {
    @Insert
    suspend fun insertBascet(bascet: Basket)

    @Query("SELECT * FROM Basket")
    fun getAllBascet(): LiveData<List<Basket>>

    @Query("DELETE FROM Basket WHERE name = :name")
    suspend fun deleteBascetByName(name: String)
}
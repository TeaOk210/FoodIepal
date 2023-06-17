package com.example.FoodIepal.Utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.FoodIepal.Entities.Bascet

@Dao
interface BascetDao {

    @Insert
    suspend fun insertBascet(bascet: Bascet)

    @Query("SELECT * FROM Basсet")
    fun getAllBascet(): LiveData<List<Bascet>>

    @Query("DELETE FROM Basсet WHERE name = :name")
    suspend fun deleteBascetByName(name: String)
}
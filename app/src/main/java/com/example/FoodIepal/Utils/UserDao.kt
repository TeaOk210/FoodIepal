package com.example.FoodIepal.Utils

import androidx.room.Dao
import androidx.room.Insert
import com.example.FoodIepal.Entities.User

@Dao
interface UserDao {
    @Insert(entity = User::class)
    suspend fun insertUser(user: User)
}
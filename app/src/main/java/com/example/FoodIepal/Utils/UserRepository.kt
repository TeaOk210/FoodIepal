package com.example.FoodIepal.Utils

import com.example.FoodIepal.Entities.User

class UserRepository(private val dao: UserDao) {
    suspend fun insertUser(user: User) = dao.insertUser(user)
}
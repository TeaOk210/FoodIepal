package com.example.FoodIepal.Utils

import com.example.FoodIepal.Entities.User

class UserRepository(private val dao: UserDao) {

    suspend fun insertUser(user: User) = dao.insertUser(user)

    suspend fun checkUserName(name: String): Boolean = dao.checkUserName(name)

    suspend fun checkLoginAndPassword(login: String, password: String): Boolean =
        dao.checkLoginAndPassword(login, password)
}
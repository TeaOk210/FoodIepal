package com.example.FoodIepal.Utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.FoodIepal.Entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT EXISTS(SELECT 1 FROM User WHERE username = :username LIMIT 1)")
    suspend fun checkUserName(username: String): Boolean

    @Query(
        "SELECT EXISTS (\n" +
                "    SELECT 1\n" +
                "    FROM User\n" +
                "    WHERE username = :login\n" +
                "      AND password = :password\n" +
                "    LIMIT 1\n" +
                ") as userExists;"
    )
    suspend fun checkLoginAndPassword(login: String, password: String): Boolean
}
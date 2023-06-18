package com.example.FoodIepal.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.Entities.User

@Database(
    version = 2,
    entities = [
        Basket::class,
        User::class
    ]
)
abstract class DataBase : RoomDatabase() {

    abstract fun getBascetDao(): BasketDao
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        private const val DB_NAME = "FoodIepal_database.db"

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
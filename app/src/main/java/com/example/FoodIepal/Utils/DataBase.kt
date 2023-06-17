package com.example.FoodIepal.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.FoodIepal.Entities.Bascet
import com.example.FoodIepal.Entities.Person
import com.example.FoodIepal.Entities.Recipe

@Database(
    version = 1,
    entities = [
        Person::class,
        Bascet::class,
        Recipe::class
    ]
)
abstract class DataBase : RoomDatabase() {

    abstract fun getBascetDao(): BascetDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        private const val DB_NAME = "recipe_database.db"

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
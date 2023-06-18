package com.example.FoodIepal.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.FoodIepal.Entities.Basket

@Database(
    version = 2,
    entities = [
        Basket::class
    ]
)
abstract class DataBase : RoomDatabase() {

    abstract fun getBascetDao(): BasketDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        private const val DB_NAME = "recipesDataase_val2.db"

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
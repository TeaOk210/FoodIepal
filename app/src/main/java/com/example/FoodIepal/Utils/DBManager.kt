package com.example.FoodIepal.Utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class DBManager(private val context: Context) {
        private lateinit var dbHelper : DataBaseHalper
        private lateinit var database : SQLiteDatabase

        fun open(): DBManager {
            dbHelper = DataBaseHalper(context)
            database = dbHelper.writableDatabase
            return this
        }

        fun close() {
            dbHelper.close()
        }

        fun insertReg(Login: String, Password: String) {
            val contentValue = ContentValues()
            contentValue.put(DataBaseHalper.Login, Login)
            contentValue.put(DataBaseHalper.Password, Password)
            database.insert(DataBaseHalper.Table_Name, null, contentValue)

            Toast.makeText(context, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
        }

        fun insertFavorite(RecipeName: String, Description: String, Recipe_Items: String, Calories: Int, Cook_time: Int, Image_path: Int){
            val contentValues = ContentValues()
            contentValues.put(DataBaseHalper.Recipe_NAme, RecipeName)
            contentValues.put(DataBaseHalper.Description, Description)
            contentValues.put(DataBaseHalper.Recipe_Items, RecipeName)
            contentValues.put(DataBaseHalper.Calories, Calories)
            contentValues.put(DataBaseHalper.Cook_time, Cook_time)
            contentValues.put(DataBaseHalper.Image_parh, Image_path)
            database.insert(DataBaseHalper.Table_Name_Favorite, null, contentValues)
        }


        fun fetchReg() : Cursor {
            val colums : Array<String> = arrayOf(
                DataBaseHalper._ID,
                DataBaseHalper.Login,
                DataBaseHalper.Password
            )
            val Cursor = database.query(DataBaseHalper.Table_Name, colums, null, null, null, null, null)
            if(Cursor!= null) {
                Cursor.moveToFirst()
            }
        return Cursor
        }

        fun fetchRecipe(): Cursor {
            val colums : Array<String> = arrayOf(
                DataBaseHalper.ID,
                DataBaseHalper.Recipe_NAme,
                DataBaseHalper.Description,
                DataBaseHalper.Recipe_Items,
                DataBaseHalper.Calories,
                DataBaseHalper.Cook_time,
                DataBaseHalper.Image_parh
            )
            val Cursor = database.query(DataBaseHalper.Table_Name_Food, colums, null, null, null, null, null)
            if (Cursor != null) {
                Cursor.moveToFirst()
            }
            return Cursor
        }

        fun fetchFavorite(): Cursor{
            val colums : Array<String> = arrayOf(
                DataBaseHalper.ID,
                DataBaseHalper.Recipe_NAme,
                DataBaseHalper.Description,
                DataBaseHalper.Recipe_Items,
                DataBaseHalper.Calories,
                DataBaseHalper.Cook_time,
                DataBaseHalper.Image_parh
            )
            val Cursor = database.query(DataBaseHalper.Table_Name_Favorite, colums, null, null, null, null, null)
            if (Cursor != null) {
                Cursor.moveToFirst()
            }
            return Cursor
        }


    fun deleteRecipe(name: String) {
        val selection = "${DataBaseHalper.Recipe_NAme}=?"
        val selectionArgs = arrayOf(name)
        database.delete(DataBaseHalper.Table_Name_Favorite, selection, selectionArgs)
    }

}
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

        fun updateReg(_id : Long, Login : String, Password: String) {
            val contentValue = ContentValues()
            contentValue.put(DataBaseHalper.Login, Login)
            contentValue.put(DataBaseHalper.Password, Password)
            val i : Int = database.update(DataBaseHalper.Table_Name, contentValue, DataBaseHalper._ID + " = " + _id, null)
        }

        fun deleteReg(_id: Long) {
            database.delete(DataBaseHalper.Table_Name, DataBaseHalper._ID + "=" + _id, null)
        }
}
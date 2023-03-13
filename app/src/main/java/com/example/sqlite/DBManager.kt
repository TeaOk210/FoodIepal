package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBManager(c: Context) {
        private lateinit var dbHelper : DataBaseHalper
        private lateinit var  context: Context
        private lateinit var database : SQLiteDatabase

        fun open() : DBManager {
            dbHelper = DataBaseHalper(context)
            database = dbHelper.writableDatabase
            return this
        }

        fun close() {
            dbHelper.close()
        }

        fun insert(Login : String, Password : String) {
            val contentValue = ContentValues()
            contentValue.put(DataBaseHalper.Login, Login)
            contentValue.put(DataBaseHalper.Password, Password)
            database.insert(DataBaseHalper.Table_Name, null, contentValue)
        }

        fun fetch() : Cursor {
            val colums : Array<String> = arrayOf(DataBaseHalper._ID, DataBaseHalper.Login, DataBaseHalper.Password)
            val Cursor = database.query(DataBaseHalper.Table_Name, colums, null, null, null, null, null)
            if(Cursor!= null) {
                Cursor.moveToFirst()
            }
        return Cursor
        }

        fun update(_id : Long, Login : String, Password: String) {
            val contentValue = ContentValues()
            contentValue.put(DataBaseHalper.Login, Login)
            contentValue.put(DataBaseHalper.Password, Password)
            val i : Int = database.update(DataBaseHalper.Table_Name, contentValue, DataBaseHalper._ID + " = " + _id, null)
        }

        fun delete(_id: Long) {
            database.delete(DataBaseHalper.Table_Name, DataBaseHalper._ID+ "=" + _id, null)
        }
}
package com.example.sqlite.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {

    companion object {
         val Table_Name = "Person"

         val _ID = "_id"
         val Login = "Login"
         val Password = "Password"

         val DB_NAME = "REG.db"
         val DB_VERSION = 2

        val CREATE_TABLE = ("CREATE TABLE "
                + Table_Name + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Login + " TEXT, " + Password + " TEXT );")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(" DROP TABLE IF EXISTS " + Table_Name)
        onCreate(db)
    }
}
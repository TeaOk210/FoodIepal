package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val Table_Name = "Person"

        private val _ID = "_id"
        private val Name = "Name"
        private val Login = "Login"
        private val Password = "Password"

        private val DB_NAME = "REG"
        private val DB_VERSION = 1

        private val CREATE_TABLE = ("CREATE TABLE "
                + Table_Name + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + Login + Password + " TEXT );")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CREATE_TABLE)
        onCreate(db)
    }
}
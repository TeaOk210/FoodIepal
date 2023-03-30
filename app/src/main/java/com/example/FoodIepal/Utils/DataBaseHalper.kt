package com.example.FoodIepal.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {

    companion object {
         val Table_Name = "Person"
//         val Table_Name_Food = "Recipes"

         val _ID = "_id"
         val Login = "Login"
         val Password = "Password"

//         val ID = "_id"
//         val RecipeCount = "Count"

         val DB_NAME = "FoodIepal.db"
         val DB_VERSION = 3

        val CREATE_TABLE = ("CREATE TABLE "
                + Table_Name + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Login + " TEXT, " + Password + " TEXT );")
//        val CREATE_TABLE_FOOD = ("CREATE TABLE"
//                + Table_Name_Food + "(" + ID
//                + " INTEGER PRIMARY KEY AUTOINCREMENT," + RecipeCount + "INTEGER );")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
//        db.execSQL(CREATE_TABLE_FOOD)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(" DROP TABLE IF EXISTS " + Table_Name)
//        db.execSQL(" DROP TABLE IF EXISTS " + Table_Name_Food)
        onCreate(db)
    }
}
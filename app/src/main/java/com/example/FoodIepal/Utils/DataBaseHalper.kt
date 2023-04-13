package com.example.FoodIepal.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {

    companion object {
         val Table_Name = "Person"
         val Table_Name_Food = "Recipes"

         val _ID = "_id"
         val Login = "Login"
         val Password = "Password"

         val ID = "_id"
         val Recipe_NAme = "recipe_name"
         val Description = "description"
         val Recipe_Items = "items"
         val Calories = "calories"
         val Cook_time = "cook_time"

         val DB_NAME = "FoodIepal.db"
         val DB_VERSION = 6

        val CREATE_TABLE = ("CREATE TABLE "
                + Table_Name + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Login + " TEXT, " + Password + " TEXT);")
        val CREATE_TABLE_FOOD = ("CREATE TABLE "
                + Table_Name_Food + "(" + ID
              + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Recipe_NAme + " TEXT, " + Description + " TEXT, " + Recipe_Items + " TEXT, " + Calories + " TEXT, " + Cook_time + " TEXT);")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE_FOOD)

        db.execSQL("insert into $Table_Name_Food($Recipe_NAme, $Description, $Recipe_Items, $Calories, $Cook_time) values('Test', 'Test', 'Test', 'Test', 'Test') ")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $Table_Name")
        db.execSQL("DROP TABLE IF EXISTS $Table_Name_Food")
        onCreate(db)
    }
}
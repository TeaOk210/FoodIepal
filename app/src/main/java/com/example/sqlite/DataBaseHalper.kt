package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHalper (Context : Context) : SQLiteOpenHelper(Context, Table_Name , _ID, Name ) {

    companion object{
        val Table_Name = "Person"

        val _ID = "_id"
        val Name = "Name"
        val Login = "Login"
        val Password = "Password"


    }
}
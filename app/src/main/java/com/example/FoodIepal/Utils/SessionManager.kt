package com.example.FoodIepal.Utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager{
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    public fun sessionManager(context: Context){
        sharedPreferences = context.getSharedPreferences("appkey", 0)
        editor = sharedPreferences.edit()
        editor.commit()
    }

    public fun setLogin(login: Boolean){
        editor.putBoolean("login", login)
        editor.commit()
    }

    public fun getLogin(): Boolean{
        return sharedPreferences.getBoolean("login", false)
    }

    public fun setUserName(username: String){
        editor.putString("username", username)
    }

    public fun getUserName(): String{
        return sharedPreferences.getString("username", false.toString()).toString()
    }
}
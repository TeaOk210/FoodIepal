package com.example.FoodIepal

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("appkey", 0)
        editor = sharedPreferences.edit()
        editor.commit()
    }

    fun setLogin(login: Boolean) {
        editor.putBoolean("login", login)
        editor.commit()
    }

    fun getLogin(): Boolean {
        return sharedPreferences.getBoolean("login", false)
    }

    fun setUserName(username: String) {
        editor.putString("username", username)
        editor.commit()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("username", false.toString()).toString()
    }
}

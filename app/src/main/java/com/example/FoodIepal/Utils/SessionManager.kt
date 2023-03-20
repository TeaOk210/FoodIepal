package com.example.FoodIepal.Utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class SessionManager {
    lateinit var pref : SharedPreferences
    lateinit var editor : Editor
    lateinit var _context : Context

    val PRIVATE_MODE : Int = 0


}
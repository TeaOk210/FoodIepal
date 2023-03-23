//package com.example.FoodIepal.Utils
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import com.example.FoodIepal.MainMenu
//
//class SessionManager {
//    lateinit var pref : SharedPreferences
//    lateinit var editor : SharedPreferences.Editor
//    lateinit var context : Context
//
//    val Private_mode : Int = 0
//
//    private val PREF_NAME : String = "AndroidHivePref"
//    private val IS_LOGIN : String = "IsLogginIn"
//    private val KEY_PASSWORD : String = "password"
//
//    @SuppressLint("NotConstructor")
//    fun SessionManager(context: Context) {
//        this.context = context
//        pref = context.getSharedPreferences(PREF_NAME, Private_mode)
//        editor = pref.edit()
//    }
//
//    fun createLoginSession(password : String) {
//        editor.putString(IS_LOGIN, true.toString())
//        editor.putString(KEY_PASSWORD, password)
//        editor.commit()
//    }
//
//    fun IsLogginIn(): Boolean {
//        return pref.getBoolean(IS_LOGIN, false)
//    }
//
//    fun checkLogin(){
//        if(!this.IsLogginIn()){
//            var intent = Intent(context, MainMenu::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//
//            context.startActivity(intent)
//        }
//    }
//
//    fun getUserDetails() {
//        var user : HashMap<String, String> = HashMap<String, String>()
//
//        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null).toString())
//    }
//}
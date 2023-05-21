package com.example.FoodIepal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Fragments.fragment_login
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding : ActivitySignInBinding
    lateinit var dbManager: DBManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)

        dbManager = DBManager(this)

        setContentView(binding.root)

        dbManager.open()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.LoginLog, fragment_login.newInstance())
            .commit()


        binding.Up.setOnClickListener() {
            val loginFragment = supportFragmentManager.findFragmentById(R.id.LoginLog) as fragment_login
            loginFragment.checkLoginAndPassword()
        }
    }

    fun OnClickSign(view: View) {
        val Register = Intent(this, MainActivity::class.java)
        startActivity(Register)
    }
}
 
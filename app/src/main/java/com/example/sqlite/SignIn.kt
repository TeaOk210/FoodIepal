package com.example.sqlite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlite.Fragments.fragment_login
import com.example.sqlite.Fragments.fragment_registr
import com.example.sqlite.Utils.DBManager
import com.example.sqlite.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding : ActivitySignInBinding
    lateinit var dbManager: DBManager
    private lateinit var loginFragment: fragment_login
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

        binding.In.setOnClickListener() {
            val Register = Intent(this, MainActivity::class.java)
            startActivity(Register)
        }

        binding.Up.setOnClickListener() {
            val loginFragment = supportFragmentManager.findFragmentById(R.id.LoginLog) as fragment_login
            loginFragment.checkLoginAndPassword()
        }
    }
}
 
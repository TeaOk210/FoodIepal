package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var dbManager: DBManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbManager = DBManager(this)
        dbManager.open()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.password, fragment_pass.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Login, fragment_login.newInstance())
            .commit()

        binding.In.setOnClickListener() {
            val Login = Intent(this, SignIn::class.java)
            startActivity(Login)
        }

            //binding.Up.setOnClickListener() {
            //val Login = binding.Login.toString()
            //val Password = binding.password.toString()
            //dbManager.update(_id = Long.MIN_VALUE, Login, Password)
            //}
    }
}
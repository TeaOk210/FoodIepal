package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.password, fragment_pass.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Login, fragment_login.newInstance())
            .commit()
        }

}
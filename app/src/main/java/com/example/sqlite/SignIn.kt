package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.passwordLog, fragment_pass.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.LoginLog, fragment_login.newInstance())
            .commit()

        binding.In.setOnClickListener() {
            val Register = Intent(this, MainActivity::class.java)
            startActivity(Register)
        }
    }
}

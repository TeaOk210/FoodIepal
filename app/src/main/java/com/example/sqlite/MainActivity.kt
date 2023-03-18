package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.Fragments.fragment_registr
import com.example.sqlite.Utils.DBManager
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var dbManager: DBManager
    private lateinit var registrationFragment: fragment_registr
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbManager = DBManager(this)
        dbManager.open()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Registr, fragment_registr.newInstance())
            .commit()


        binding.In.setOnClickListener() {
            val Login = Intent(this, SignIn::class.java)
            startActivity(Login)
        }

        binding.Up.setOnClickListener() {
            registrationFragment = supportFragmentManager.findFragmentById(R.id.Registr) as fragment_registr
            if (registrationFragment.checkRegistrationFields()) {
                registrationFragment.Registration()
                val Main = Intent(this, MainMenu::class.java)
                startActivity(Main)
            }
        }
    }
}
package com.example.FoodIepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.FoodIepal.Fragments.fragment_registr
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.databinding.ActivityMainBinding

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


        binding.Up.setOnClickListener() {
            registrationFragment = supportFragmentManager.findFragmentById(R.id.Registr) as fragment_registr
            if (registrationFragment.checkRegistrationFields()) {
                registrationFragment.Registration()
                val Main = Intent(this, MainMenu::class.java)
                startActivity(Main)
            }
        }
    }

    fun OnClickReg(view: View) {
        val Login = Intent(this, SignIn::class.java)
        startActivity(Login)
    }
}
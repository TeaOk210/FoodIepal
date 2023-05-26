package com.example.FoodIepal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Fragments.fragment_registr
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var registrationFragment: fragment_registr
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)
        if (sessionManager.getLogin()){
            val Main = Intent(this, MainMenu::class.java)
            startActivity(Main)
        }


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



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

    fun skip(view: View) {
        sessionManager.setLogin(false)
        sessionManager.setUserName("guest")

        val Main = Intent(this, MainMenu::class.java)
        startActivity(Main)
    }
}
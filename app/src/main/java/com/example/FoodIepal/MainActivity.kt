package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Fragments.FragmentRegistr
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var registrationFragment: FragmentRegistr
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.Registr, FragmentRegistr.newInstance())
            .commit()

        binding.Up.setOnClickListener() {
            registrationFragment =
                supportFragmentManager.findFragmentById(R.id.Registr) as FragmentRegistr
            if (registrationFragment.checkRegistrationFields()) {
                registrationFragment.registr()
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    fun onClickReg(view: View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }

    fun skip(view: View) {
        sessionManager.setLogin(false)
        sessionManager.setUserName("guest")

        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
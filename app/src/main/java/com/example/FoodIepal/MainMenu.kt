package com.example.FoodIepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.FoodIepal.Fragments.Fragment_HomeMenu
import com.example.FoodIepal.databinding.ActivityMainMenuBinding

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.MenuFrag, Fragment_HomeMenu.newInstance())
            .commit()
    }
}

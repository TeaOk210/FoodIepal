package com.example.FoodIepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.FoodIepal.Fragments.FragmentHome
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding
    val fragment = FragmentHome()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickExitListener(view: View) {
        finish()
    }

    fun onClickContinueListener(view: View) {
        finish()
        val bundle = Bundle()
        val intent = Intent(this, MainMenu::class.java)
        bundle.putInt("minKk", binding.minKk.text.toString().toIntOrNull()!!)
        bundle.putInt("maxKk", binding.maxKk.text.toString().toIntOrNull()!!)
        intent.getBundleExtra("Data")
    }
}
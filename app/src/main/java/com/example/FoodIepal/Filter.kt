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
        val bundle = Bundle()
        bundle.putString("minKk", binding.minKk.toString())
        bundle.putString("maxKk", binding.maxKk.toString())
        fragment.arguments = bundle
        fragment.getData()
        finish()
    }
}
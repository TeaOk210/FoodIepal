package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.FoodIepal.Fragments.FragmentHome
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding
    private val dataModel: DataModel by viewModels()

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
    }

}
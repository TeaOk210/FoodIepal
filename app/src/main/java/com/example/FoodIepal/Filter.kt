package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onClickExitListener(view: View) {
        finish()
    }
    fun onClickContinueListener(view: View) {
        intent = Intent(this@Filter, MainMenu::class.java)
        val Infinity: Double = 1.0/0.0
        val minKk = binding.minKk.text.toString().toIntOrNull() ?: 0
        val maxKk = binding.maxKk.text.toString().toIntOrNull() ?: 0
        val minTt = binding.timeMin.text.toString().toIntOrNull() ?: Infinity.toString().toInt()
        val maxTt = binding.timeMax.text.toString().toIntOrNull() ?: Infinity.toString().toInt()
        intent.putExtra("minKk", minKk)
        intent.putExtra("timeMin", minTt)
        intent.putExtra("maxKk", maxKk)
        intent.putExtra("timeMax", maxTt)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
    companion object {
        const val REQUEST_CODE = 228
    }
}
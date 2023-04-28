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
        val minKk = if (binding.minKk.text.isNotEmpty()) binding.minKk.text.toString().toInt() else 0
        val maxKk = if (binding.maxKk.text.isNotEmpty()) binding.maxKk.text.toString().toInt() else Int.MAX_VALUE
        val minTt = if (binding.timeMin.text.isNotEmpty()) binding.timeMin.text.toString().toInt() else 0
        val maxTt = if (binding.timeMax.text.isNotEmpty()) binding.timeMax.text.toString().toInt() else Int.MAX_VALUE
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
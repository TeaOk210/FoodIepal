package com.example.FoodIepal.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    fun onClickExitListener(view: View) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun onClickContinueListener(view: View) {
        val minKk = if (binding.minKk.text.isNotEmpty()) binding.minKk.text.toString().toInt() else 0
        val maxKk = if (binding.maxKk.text.isNotEmpty()) binding.maxKk.text.toString().toInt() else Int.MAX_VALUE
        val minTt = if (binding.timeMin.text.isNotEmpty()) binding.timeMin.text.toString().toInt() else 0
        val maxTt = if (binding.timeMax.text.isNotEmpty()) binding.timeMax.text.toString().toInt() else Int.MAX_VALUE

        val item1: String = binding.item1.text.toString()
        val item2: String = binding.item2.text.toString()
        val item3: String = binding.item3.text.toString()

        val itemsField: ArrayList<String> = arrayListOf(item1, item2, item3)
        val itemsList: ArrayList<String> = arrayListOf()

        for (item in itemsField){
            if (item.isNotEmpty()){
                itemsList.add(item)
            }
        }

        val result = Intent()
        result.putExtra("minKk", minKk)
        result.putExtra("timeMin", minTt)
        result.putExtra("maxKk", maxKk)
        result.putExtra("timeMax", maxTt)
        result.putExtra("items", itemsList)

        setResult(Activity.RESULT_OK, result)
        finish()
    }
}
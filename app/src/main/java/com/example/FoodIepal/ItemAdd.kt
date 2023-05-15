package com.example.FoodIepal

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.databinding.ActivityItemAddBinding

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    lateinit var dbManager: DBManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbManager = DBManager(this)
        dbManager.open()
        binding = ActivityItemAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickExit(view: View){
        finish()
    }

    fun onAddListener(view: View) {
        dbManager.insertBasket(binding.NameInput.text.toString(), binding.DoseInput.text.toString())
        finish()
    }
}
package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.databinding.ActivityItemAddBinding

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickExit(view: View){
        finish()
    }

    fun onAddListener(view: View) {
        intent = Intent(this@ItemAdd, MainMenu::class.java)
        intent.putExtra("Name", binding.NameInput.text.toString())
        intent.putExtra("Dose", binding.DoseInput.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
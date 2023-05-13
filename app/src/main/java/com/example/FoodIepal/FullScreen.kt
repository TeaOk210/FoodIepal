package com.example.FoodIepal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.databinding.ActivityFullScreenBinding

class FullScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        getData()
        setContentView(binding.root)
    }

    private fun getData(){
        val intent = Intent(this, MainMenu::class.java)
        val name = intent.getStringExtra("name")
        val text = intent.getStringExtra("text")
        val time = intent.getStringExtra("time")
        val Kkal = intent.getStringExtra("Kkal")
        val image = intent.getStringExtra("RecipeImageResId")
        Log.e("name", name.toString())
        Log.e("text", text.toString())
        Log.e("time", time.toString())
        Log.e("Kkal", Kkal.toString())
        Log.e("image", image.toString())
    }
}
package com.example.FoodIepal

import android.os.Bundle
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
        val name = intent.getStringExtra("name")
        val text = intent.getStringExtra("text")
        val time = intent.getIntExtra("time", 0)
        val Kkal = intent.getIntExtra("Kkal", 0)
        val image = intent.getIntExtra("RecipeImageResId", 0)

        binding.KkalView.text = Kkal.toString()
        binding.TimeView.text = time.toString()
    }
}

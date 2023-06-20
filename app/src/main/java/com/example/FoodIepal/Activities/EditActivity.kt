package com.example.FoodIepal.Activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.R
import com.example.FoodIepal.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)
        getData()

        setContentView(R.layout.activity_edit)
    }

    private fun getData() {
        binding.apply {
            intent.apply {
                val Kkal = getIntExtra("Kkal", 0)
                KkalView.setText(Kkal.toString())
            }
        }

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_button)
        }
    }
}
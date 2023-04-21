package com.example.FoodIepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickContinueListener(view: View) {

    }

    fun onClickExitListener(view: View) {
        finish()
    }

    public interface OnDataPass {
        fun onDataPass(data: String) {

        }
    }
}
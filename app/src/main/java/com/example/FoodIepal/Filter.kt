package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.FoodIepal.Fragments.FragmentHome
import com.example.FoodIepal.databinding.ActivityFilterBinding

class Filter : AppCompatActivity() {
    lateinit var binding: ActivityFilterBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        binding.minKk.text =  dataModel.
        setContentView(binding.root)
    }

    fun onClickExitListener(view: View) {
        finish()
    }

    fun onClickContinueListener(view: View) {
        intent = Intent(this@Filter, MainMenu::class.java)
        val Infinity: Double = 1.0/0.0
        intent.putExtra("minKk", binding.minKk.text.toString().toIntOrNull() ?: 0)
        intent.putExtra("timeMin", binding.minKk.text.toString().toIntOrNull() ?: 0)
        intent.putExtra("maxKk", binding.maxKk.text.toString().toIntOrNull() ?: Infinity.toInt())
        intent.putExtra("timeMax", binding.maxKk.text.toString().toIntOrNull() ?: Infinity.toInt())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val REQUEST_CODE = 228
    }

}
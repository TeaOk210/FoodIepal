package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.FoodIepal.Fragments.FragmentHome
import com.example.FoodIepal.databinding.ActivityFilterBinding
import kotlin.math.max
import kotlin.math.min

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
        changeText(minKk, maxKk, minTt, maxTt)
        finish()
    }

    private fun changeText(minKk: Int, maxKk: Int, minTt: Int, maxTt:Int) {
        binding.minKk.setText(minKk.toString())
        binding.maxKk.setText(maxKk.toString())
        binding.timeMin.setText(minTt.toString())
        binding.timeMax.setText(maxTt.toString())
    }

    companion object {
        const val REQUEST_CODE = 228
    }

}
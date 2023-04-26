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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainMenu.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val minKk = data?.getIntExtra("minKk", 0) ?: 0
                val timeMin = data?.getIntExtra("minTt", 0) ?: 0
                val maxKk = data?.getIntExtra("maxKk", 0) ?: 0
                val timeMax = data?.getIntExtra("maxTt", 0) ?: 0
                binding.minKk.setText(minKk)
                binding.maxKk.setText(maxKk)
                binding.timeMin.setText(timeMin)
                binding.timeMax.setText(timeMax)
            }
        }
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
        finish()
    }
    companion object {
        const val REQUEST_CODE = 228
    }
}
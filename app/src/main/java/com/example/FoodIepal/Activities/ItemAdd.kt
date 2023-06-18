package com.example.FoodIepal.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.VIew.BascetViewModel
import com.example.FoodIepal.databinding.ActivityItemAddBinding

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: BascetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[BascetViewModel::class.java]

        binding = ActivityItemAddBinding.inflate(layoutInflater)

        sessionManager = SessionManager(this)

        setContentView(binding.root)
    }

    fun onClickExit(view: View) {
        finish()
    }

    fun onAddListener(view: View) {
        val name = binding.NameInput.text.toString()
        val dose = binding.DoseInput.text.toString()
//        val login = sessionManager.getUserName()

        if (name.isNotEmpty() && dose.isNotEmpty()) {
            viewModel.insertBascet(Basket(name, dose))
            finish()
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.FoodIepal.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.databinding.ActivityItemAddBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = DataBase.getDatabase(this)

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
        val login = sessionManager.getUserName()

        if (name.isNotEmpty() && dose.isNotEmpty()) {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.getBascetDao().insertBascet(Basket(name, dose, login))
                }
            }
            finish()
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}
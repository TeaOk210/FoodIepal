package com.example.FoodIepal

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityItemAddBinding

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbManager = DBManager(this)
        dbManager.open()

        binding = ActivityItemAddBinding.inflate(layoutInflater)

        sessionManager = SessionManager(this)

        setContentView(binding.root)
    }

    fun onClickExit(view: View){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun onAddListener(view: View) {
        val name = binding.NameInput.text.toString()
        val dose = binding.DoseInput.text.toString()
        val login = sessionManager.getUserName()

        if (name.isNotEmpty() && dose.isNotEmpty()) {
            dbManager.insertBasket(name, dose, login)
            setResult(Activity.RESULT_OK)
            finish()
        } else{
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}
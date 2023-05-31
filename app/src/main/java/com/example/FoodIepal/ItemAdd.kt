package com.example.FoodIepal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Fragments.FragmentBascet
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.MyPagerAdapter
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityItemAddBinding

class ItemAdd : AppCompatActivity() {
    lateinit var binding: ActivityItemAddBinding
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: MyPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbManager = DBManager(this)
        dbManager.open()

        adapter = MyPagerAdapter(this)

        binding = ActivityItemAddBinding.inflate(layoutInflater)

        sessionManager = SessionManager(this)

        setContentView(binding.root)
    }

    fun onClickExit(view: View){
        finish()
    }

    fun onAddListener(view: View) {
        val name = binding.NameInput.text.toString()
        val dose = binding.DoseInput.text.toString()
        val login = sessionManager.getUserName()
        val fragmentBascet = adapter.getFragment(1) as? FragmentBascet

        if (name.isNotEmpty() && dose.isNotEmpty()) {
            dbManager.insertBasket(name, dose, login)
            fragmentBascet?.setUpAdapter()
            fragmentBascet?.populateList()
            finish()
        } else{
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}
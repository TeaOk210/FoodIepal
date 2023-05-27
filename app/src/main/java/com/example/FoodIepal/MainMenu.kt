package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding
    lateinit var BottomNav : BottomNavigationView
    lateinit var bundle: Bundle
    private val dataModel: DataModel by viewModels()
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)
        if (!sessionManager.getLogin()){
            startActivityForResult(Intent(this, MainActivity::class.java), 2)
        }

        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadFragment(FragmentHome.newInstance())

        BottomNav = binding.BottomMenu
        BottomNav.setOnItemSelectedListener{
           when(it.itemId) {
               R.id.home -> {
                   loadFragment(FragmentHome.newInstance())
                   true
               }
               R.id.bascet -> {
                   loadFragment(FragmentBascet.newInstance())
                   true
               }
               R.id.favorites -> {
                   loadFragment(FragmentFavorite.newInstance())
                   true
               }
               else -> {
                   false
               }
           }
       }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MenuFrag,fragment)
        transaction.commit()
    }


    fun onClickFilterListener(view: View) {
        val intent = Intent(this@MainMenu, Filter::class.java)
        startActivityForResult(intent, 1)
    }

    fun onClickItemAdd(item: MenuItem) {
        val intent = Intent(this@MainMenu, ItemAdd::class.java)
        startActivityForResult(intent, 3)
    }

    fun clearFilter(item: MenuItem) {
        dataModel.minKk.value = 0
        dataModel.maxKk.value = Int.MAX_VALUE
        dataModel.minTt.value = 0
        dataModel.maxTt.value = Int.MAX_VALUE
        dataModel.items.value = ArrayList()
        Toast.makeText(this, "Фильтр очищен!", Toast.LENGTH_SHORT).show()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val minKk = data?.getIntExtra("minKk", 0) ?: 0
                val timeMin = data?.getIntExtra("timeMin", 0) ?: 0
                val maxKk = data?.getIntExtra("maxKk", 0) ?: Int.MAX_VALUE
                val timeMax = data?.getIntExtra("timeMax", 0) ?: Int.MAX_VALUE
                val items = data?.getStringArrayListExtra("items")

                dataModel.minKk.value = minKk
                dataModel.maxKk.value = maxKk
                dataModel.minTt.value = timeMin
                dataModel.maxTt.value = timeMax
                dataModel.items.value = items
            }
        }
    }
}

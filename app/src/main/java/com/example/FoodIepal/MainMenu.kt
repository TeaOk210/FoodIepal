package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.FoodIepal.Filter.Companion.REQUEST_CODE
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.max

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding
    lateinit var BottomNav : BottomNavigationView
    lateinit var lastFragment: Fragment
    lateinit var bundle: Bundle
    private val dataModel: DataModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(FragmentHome.newInstance())
        lastFragment = FragmentHome.newInstance()
        BottomNav = binding.BottomMenu
        BottomNav.setOnItemSelectedListener{
           when(it.itemId) {
               R.id.home -> {
                   loadFragment(FragmentHome.newInstance())
                   lastFragment = FragmentHome.newInstance()
                   true
               }
               R.id.bascet -> {
                   loadFragment(FragmentBascet.newInstance())
                   lastFragment = FragmentBascet.newInstance()
                   true
               }
               R.id.favorites -> {
                   loadFragment(FragmentFavorite.newInstance())
                   lastFragment = FragmentFavorite.newInstance()
                   true
               }
               else -> {
                   false
               }
           }
       }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                val minKk = data?.getIntExtra("minKk", 0) ?: 0
                val timeMin = data?.getIntExtra("timeMin", 0) ?: 0
                val maxKk = data?.getIntExtra("maxKk", 0) ?: 0
                val timeMax = data?.getIntExtra("timeMax", 0) ?: 0
                dataModel.Kkal.value = IntRange(minKk, maxKk)
                dataModel.Time.value = IntRange(timeMin, timeMax)
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MenuFrag,fragment)
        transaction.commit()
    }

    fun onClickFilterListener(view: View) {
        val intent = Intent(this, Filter::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun OnClickFullScreen(view: View){
        loadFragment(FragmentRecipeFullScreen.newInstance())
    }

}

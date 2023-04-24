package com.example.FoodIepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
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

    override fun onRestart() {
        super.onRestart()
        val Infinity: Double = 1.0/0.0
        val minKk = intent.extras?.getInt("minKk") ?: 0
        val maxKk = intent.extras?.getInt("maxKk") ?: Infinity.toInt()
        val Kkal = IntRange(minKk, maxKk)
        dataModel.Kkal.value = Kkal
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MenuFrag,fragment)
        transaction.commit()
    }

    fun onClickFilterListener(view: View) {
        val filter = Intent(this, Filter::class.java)
        startActivity(filter)
    }

    fun OnClickFullScreen(view: View){
        loadFragment(FragmentRecipeFullScreen.newInstance())
    }

}

package com.example.FoodIepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding
    lateinit var BottomNav : BottomNavigationView
    lateinit var lastFragment: Fragment
    lateinit var bundle: Bundle
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

        loadFragment(FragmentHome.newInstance())
        FragmentHome().getData()
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

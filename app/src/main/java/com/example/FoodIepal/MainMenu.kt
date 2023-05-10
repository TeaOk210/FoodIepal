package com.example.FoodIepal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MenuFrag,fragment)
        transaction.commit()
    }
    fun onClickFilterListener(view: View) {
        val intent = Intent(this@MainMenu, Filter::class.java)
        startActivityForResult(intent, 1)
    }
    fun OnClickFullScreen(view: View){
        loadFragment(FragmentRecipeFullScreen.newInstance())
    }
    fun onClickItemAdd(view: View){
        val intent = Intent(this@MainMenu, ItemAdd::class.java)
        startActivityForResult(intent, 2)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val minKk = data?.getIntExtra("minKk", 0) ?: 0
                val timeMin = data?.getIntExtra("timeMin", 0) ?: 0
                val maxKk = data?.getIntExtra("maxKk", 0) ?: 0
                val timeMax = data?.getIntExtra("timeMax", 0) ?: 0
                dataModel.minKk.value = minKk
                dataModel.maxKk.value = maxKk
                dataModel.minTt.value = timeMin
                dataModel.maxTt.value = timeMax
            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                val Name = data?.getStringExtra("Name")
                val Dose = data?.getStringExtra("Dose")
                dataModel.Name.value = Name
                dataModel.Dose.value = Dose
            }
        }
    }
}

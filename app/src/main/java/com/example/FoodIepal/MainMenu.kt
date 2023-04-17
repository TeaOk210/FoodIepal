package com.example.FoodIepal

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.Utils.FatSecretGet
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONException
import org.json.JSONObject

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding
    lateinit var BottomNav : BottomNavigationView
    private val RecipeItemList = ArrayList<RecipeItem>()
    private lateinit var adapter: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
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

    fun OnClickFullScreen(view: View){
        loadFragment(FragmentRecipeFullScreen.newInstance())
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<RecipeItem> = ArrayList()
        for (item in RecipeItemList) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.text.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.time.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.Kkal.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        adapter.filteredList(filteredlist)
        if (filteredlist.isEmpty()) {
            Toast.makeText(this@MainMenu, "Такого нет...", Toast.LENGTH_SHORT).show()
        }
    }
}

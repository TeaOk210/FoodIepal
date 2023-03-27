package com.example.FoodIepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.FoodIepal.Fragments.FragmentBascet
import com.example.FoodIepal.Fragments.FragmentFavorite
import com.example.FoodIepal.Fragments.FragmentHome
import com.example.FoodIepal.Fragments.FragmentRecipeFullScreen
import com.example.FoodIepal.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenu : AppCompatActivity() {
    lateinit var binding : ActivityMainMenuBinding
    lateinit var BottomNav : BottomNavigationView
   private lateinit var adapter: MyPagerAdapter
   private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyPagerAdapter(this)
        viewPager = binding.ViewPager
        viewPager.adapter = adapter
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

//    fun OnClickFullScreen(view: View){
//        loadFragment(FragmentRecipeFullScreen.newInstance())
//    }
}

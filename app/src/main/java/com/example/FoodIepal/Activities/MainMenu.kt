package com.example.FoodIepal.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.FoodIepal.Dialogs.DialogSetName
import com.example.FoodIepal.Fragments.*
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.VIew.MyPagerAdapter
import com.example.FoodIepal.databinding.ActivityMainMenuBinding

@Suppress("DEPRECATION")
class MainMenu : AppCompatActivity() {
    lateinit var binding: ActivityMainMenuBinding
    private val dataModel: DataModel by viewModels()
    private lateinit var sessionManager: SessionManager
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: MyPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)
        if (!sessionManager.getLogin()) {
            startActivityForResult(Intent(this, MainActivity::class.java), 2)
        }

        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewPager = binding.PagerView
        viewPager.adapter = MyPagerAdapter(this)

        adapter = MyPagerAdapter(this)

        viewPager.currentItem = 0
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val menuItem = when (position) {
                    0 -> R.id.Homebtn
                    1 -> R.id.bascet
                    2 -> R.id.favorites
                    else -> -1
                }
                if (menuItem != -1) {
                    binding.BottomMenu.menu.findItem(menuItem)?.isChecked = true
                }
            }
        })

        binding.BottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Homebtn -> {
                    viewPager.currentItem = 0
                    true
                }

                R.id.bascet -> {
                    viewPager.currentItem = 1
                    true
                }

                R.id.favorites -> {
                    viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }

    fun onClickItemAdd(item: MenuItem) {
        val intent = Intent(this@MainMenu, ItemAdd::class.java)
        startActivityForResult(intent, 3)
    }

    fun onClickRecipeAdd(item: MenuItem) {
        DialogSetName.newInstance().show(supportFragmentManager, "")
    }
}
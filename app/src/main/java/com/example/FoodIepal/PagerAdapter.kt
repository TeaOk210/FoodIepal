package com.example.FoodIepal

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.FoodIepal.Fragments.FragmentBascet
import com.example.FoodIepal.Fragments.FragmentFavorite
import com.example.FoodIepal.Fragments.FragmentHome

class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentHome.newInstance()
            1 -> FragmentBascet.newInstance()
            2 -> FragmentFavorite.newInstance()
            else -> FragmentHome.newInstance()
        }
    }
}
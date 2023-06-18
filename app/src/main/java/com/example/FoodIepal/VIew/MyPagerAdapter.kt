package com.example.FoodIepal.VIew

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.FoodIepal.Fragments.FragmentBascet
import com.example.FoodIepal.Fragments.FragmentFavorite
import com.example.FoodIepal.Fragments.FragmentHome

class MyPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private val fragmentList = arrayListOf<Fragment>()

    init {
        fragmentList.add(FragmentHome.newInstance())
        fragmentList.add(FragmentBascet.newInstance())
        fragmentList.add(FragmentFavorite.newInstance())
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun getFragment(position: Int): Fragment? {
        return if (position in 0 until fragmentList.size) {
            fragmentList[position]
        } else {
            null
        }
    }
}

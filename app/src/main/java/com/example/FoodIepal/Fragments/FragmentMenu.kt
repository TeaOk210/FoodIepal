package com.example.FoodIepal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.FoodIepal.MyPagerAdapter
import com.example.FoodIepal.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private lateinit var adapter: MyPagerAdapter
    private lateinit var viewPager: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        adapter = MyPagerAdapter(requireActivity())
        viewPager = binding.ViewPager
        viewPager.adapter = adapter
        return binding.root

    }

    companion object {
        fun newInstance() = FragmentMenu()
    }
}
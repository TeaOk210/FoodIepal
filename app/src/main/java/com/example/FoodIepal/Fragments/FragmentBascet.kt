package com.example.FoodIepal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentBascetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)
        setUpAdapter()
        return binding.root
    }


    fun setUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList )
        binding.BascetList.adapter = adapter
        binding.BascetList.layoutManager = LinearLayoutManager(requireActivity())
    }


    companion object {
        fun newInstance() = FragmentBascet()
    }
}
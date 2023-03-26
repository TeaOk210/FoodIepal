package com.example.FoodIepal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.RecipeAdapter
import com.example.FoodIepal.RecipeItem
import com.example.FoodIepal.databinding.FragmentBascetBinding
import com.example.FoodIepal.databinding.FragmentFavoriteBinding

class FragmentBascet : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentBascetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)
        populateList()
        setUpAdapter()
        return binding.root
    }

    fun populateList() {
        for(i in 1..20) {
            val name = "2 Recipe name $i"
            val text = "opisanie recepta"
            val time = i *10
            val Kkal = i * 200
            val recipeItem = RecipeItem(name = name, text = text, time = time, Kkal = Kkal, RecipeImageResId = R.drawable.food)
            RecipeItemList.add(recipeItem)
        }
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
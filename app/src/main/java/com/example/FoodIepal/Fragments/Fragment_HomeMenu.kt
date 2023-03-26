package com.example.FoodIepal.Fragments

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.RecipeAdapter
import com.example.FoodIepal.RecipeItem
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding


class Fragment_HomeMenu : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding : FragmentHomeMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenuBinding.inflate(inflater)
        populateList()
        SetUpAdapter()
        return binding.root
    }

    fun populateList() {
        for (i in 1..20) {
            val name = "Recipe name $i"
            val text = "opisanie recepta" + i*200
            val time = i *5
            val Kkal = i * 100
            val recipeItem = RecipeItem(name = name, text = text, time = time, Kkal = Kkal)
            RecipeItemList.add(recipeItem)
        }
    }

    fun SetUpAdapter(){
        adapter = RecipeAdapter(requireActivity(), RecipeItemList )
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = Fragment_HomeMenu()
    }
}

package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.FragmentFavoriteBinding

class FragmentFavorite : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        populateList()
        setUpAdapter()
        return binding.root
    }

    @SuppressLint("Range")
    fun populateList() {
    }

    fun setUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList, object : RecipeAdapter.OnItemClickListener{
            override fun onItemClick(data: RecipeItem){
                Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT).show()
            }
        })
        binding.FavoriteList.adapter = adapter
        binding.FavoriteList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentFavorite()
    }
}
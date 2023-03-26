package com.example.FoodIepal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.FoodIepal.R
import com.example.FoodIepal.RecipeAdapter
import com.example.FoodIepal.RecipeItem
import com.example.FoodIepal.databinding.FragmentRecipeFullScreenBinding

class FragmentRecipeFullScreen : Fragment() {
    lateinit var binding: FragmentRecipeFullScreenBinding
    lateinit var adapter: RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeFullScreenBinding.inflate(inflater)
        binding.TextViewFullScr.text = "ПОШЕЛ НАФИГ!"
        return binding.root
    }

    companion object {
        fun newInstance() = FragmentRecipeFullScreen()
    }
}
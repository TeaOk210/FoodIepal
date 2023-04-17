package com.example.FoodIepal.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding
import com.example.FoodIepal.databinding.FragmentSearchBinding

class FragmentSearch : Fragment() {

    private lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentSearchBinding
    private val RecipeItemList = ArrayList<RecipeItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {

        fun newInstance() = FragmentSearch()
    }


    private fun filter(text: String) {
        val filteredlist: ArrayList<RecipeItem> = ArrayList()
        for (item in RecipeItemList) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.text.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.time.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
            if (item.Kkal.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        adapter.filteredList(filteredlist)
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireActivity(), "Такого нет...", Toast.LENGTH_SHORT).show()
        }
    }
}
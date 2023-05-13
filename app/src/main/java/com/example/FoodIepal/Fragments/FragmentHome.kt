package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding


@Suppress("DEPRECATION")
class FragmentHome : Fragment() {
    private val RecipeItemList = ArrayList<RecipeItem>()
    private var filteredList = ArrayList<RecipeItem>()
    private lateinit var FatSecretGet: FatSecretSearch
    lateinit var dbManager: DBManager
    private lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentHomeMenuBinding
    private val dataModel: DataModel by activityViewModels()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireActivity())
        binding = FragmentHomeMenuBinding.inflate(inflater)
        dataModel.minKk.observe(activity as LifecycleOwner) {minKk ->
            dataModel.maxKk.observe(activity as LifecycleOwner) {maxkK ->
                dataModel.minTt.observe(activity as LifecycleOwner) {minTt ->
                    dataModel.maxTt.observe(activity as LifecycleOwner) {maxTt ->
                        kalTimeFilter(IntRange(minKk, maxkK), IntRange(minTt, maxTt))
                    }
                }
            }
        }
            binding.SearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
        dbManager.open()
        SetUpAdapter() // add RV
        populateList()
        return binding.root
    }

        fun kalTimeFilter(kkalRange: IntRange, timeRange: IntRange) {
        filteredList.clear()
        for (item in RecipeItemList) {
            if (item.Kkal in kkalRange && item.time in timeRange) {
                filteredList.add(item)
            }
        }
        adapter.filter(filteredList)
    }
    companion object {
        fun newInstance() = FragmentHome()
    }
    @SuppressLint("DefaultLocale")
    private fun filter(text: String) {
        val searchedList: ArrayList<RecipeItem> = ArrayList()
        for (item in filteredList) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                searchedList.add(item)
            }
        }
        adapter.filter(searchedList)
        if (RecipeItemList.isEmpty()) {
            Toast.makeText(requireActivity(), "Такого нет...", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("Range")
    private fun populateList() {
        val cursor = dbManager.fetchRecipe()
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Description))
                val time = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Calories))
                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImageResId = R.drawable.food
                )
                RecipeItemList.add(recipeItem)
                filteredList.add(recipeItem)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
    private fun SetUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList)
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }
}

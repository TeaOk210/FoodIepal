package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.DataModel
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding


@Suppress("DEPRECATION")
class FragmentHome : Fragment() {
    private val RecipeItemList = ArrayList<RecipeItem>()
    private lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentHomeMenuBinding
    private val dataModel: DataModel by activityViewModels()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenuBinding.inflate(inflater)
        dataModel.Kkal.observe(activity as LifecycleOwner) { Krange ->
            dataModel.Time.observe(activity as LifecycleOwner) {Trange ->
                kalTimeFilter(Krange, Trange)
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
        SetUpAdapter() // add RV
        populateList()
        return binding.root
    }

    fun kalTimeFilter(kkalRange: IntRange, timeRange: IntRange) {
        val filteredList: ArrayList<RecipeItem> = ArrayList()
        val unfilteredList: ArrayList<RecipeItem> = ArrayList()

        for (item in RecipeItemList) {
            if (item.Kkal in kkalRange && item.time in timeRange) {
                filteredList.add(item)
//            } else {
//                unfilteredList.add(item)
            }
        }

        filteredList.sortWith(compareBy({ it.time }, { it.Kkal }))
//        unfilteredList.sortWith(compareBy({ it.time }, { it.Kkal }))

//        filteredList.addAll(unfilteredList)
        adapter.filteredList(filteredList)
    }



    companion object {
        fun newInstance() = FragmentHome()
    }
    @SuppressLint("DefaultLocale")
    private fun filter(text: String) {
        val filteredlist: ArrayList<RecipeItem> = ArrayList()
        for (item in RecipeItemList) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        adapter.filteredList(filteredlist)
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireActivity(), "Такого нет...", Toast.LENGTH_SHORT).show()
        }
    }
    private fun populateList() {
        for (i in 1..50) {
            val name = "Recipe name"
            val text = "opisanie recepta, it is recipe number $i"
            val time = i
            val Kkal = i * 10
            val recipeItem = RecipeItem(
                name = name,
                text = text,
                time = time,
                Kkal = Kkal,
                RecipeImageResId = R.drawable.food
            )
            RecipeItemList.add(recipeItem)
        }
    }

    private fun SetUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList)
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

}

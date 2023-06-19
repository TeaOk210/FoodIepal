package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Activities.FullScreen
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.VIew.RecipeAdapter
import com.example.FoodIepal.VIew.RecipeViewModel
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding


@Suppress("DEPRECATION")
class FragmentHome : Fragment(), RecipeAdapter.OnItemClickListener{
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentHomeMenuBinding
    private val dataModel: DataModel by activityViewModels()
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: RecipeViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sessionManager = SessionManager(requireContext())

        binding = FragmentHomeMenuBinding.inflate(layoutInflater)

        binding.toolbarMenu.apply {
            title = "Главная"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.custom_toolvar_menu)
        }

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        setAdapter()
        populateList()

        dataModel.minKk.observe(viewLifecycleOwner) { minKk ->
            dataModel.maxKk.observe(viewLifecycleOwner) { maxkK ->
                dataModel.minTt.observe(viewLifecycleOwner) { minTt ->
                    dataModel.maxTt.observe(viewLifecycleOwner) { maxTt ->
                        dataModel.items.observe(viewLifecycleOwner) { items ->
                            filter(IntRange(minKk, maxkK), IntRange(minTt, maxTt), items)
                            Log.e("test", minKk.toString())
                            Log.e("test", maxkK.toString())
                            Log.e("test", minTt.toString())
                            Log.e("test", maxTt.toString())
                            Log.e("test", items.toString())
                        }
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
                    search(s.toString())
                }
            })
        return binding.root
    }

    override fun onItemClick(data: Recipe) {
        val intent = Intent(requireContext(), FullScreen::class.java)

        intent.putExtra("Kkal", data.calories)
        intent.putExtra("time", data.cookTime)
        intent.putExtra("name", data.recipeName)
        intent.putExtra("text", data.description)
        intent.putExtra("image", data.imagePath)
        intent.putExtra("items", data.recipeItems)
        intent.putExtra("preparation", data.preparation)

        startActivityForResult(intent, 1)
    }

    private fun populateList() {
        viewModel.allRecipes.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.filter(it as ArrayList<Recipe>)
            }
        }
    }

    private fun filter(kkalRange: IntRange, timeRange: IntRange, items: ArrayList<String>) {
        val filteredList = viewModel.allRecipes.value?.filter { recipe ->
            recipe.calories in kkalRange &&
                    recipe.cookTime in timeRange &&
                    (items.isEmpty() || items.all { recipe.recipeItems.contains(it) })
        }
        adapter.filter(filteredList as ArrayList<Recipe>?)
    }

    @SuppressLint("DefaultLocale")
    private fun search(text: String) {
        val searchedList = viewModel.allRecipes.value?.filter {
            it.recipeName.toLowerCase().contains(text.toLowerCase())
        }?.distinctBy { it.recipeName }

        if (searchedList.isNullOrEmpty()) {
            Toast.makeText(requireActivity(), "Такого нет...", Toast.LENGTH_SHORT).show()
        }

        adapter.filter((searchedList as ArrayList<Recipe>?)!!)
    }

    private fun setAdapter() {
        adapter = RecipeAdapter(this)
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentHome()
    }
}

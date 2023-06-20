package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Activities.Filter
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
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.clearButton -> {
                        filter(IntRange(0, Int.MAX_VALUE), IntRange(0, Int.MAX_VALUE), ArrayList())
                        Toast.makeText(requireContext(), "Фильтр очищен!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        filterStart()
        setAdapter()
        populateList()

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

    private fun filterStart() {
        binding.imageButton.setOnClickListener {
            startActivityForResult(Intent(requireContext(), Filter::class.java), 1)
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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val minKk = data?.getIntExtra("minKk", 0) ?: 0
            val timeMin = data?.getIntExtra("timeMin", 0) ?: 0
            val maxKk = data?.getIntExtra("maxKk", 0) ?: Int.MAX_VALUE
            val timeMax = data?.getIntExtra("timeMax", 0) ?: Int.MAX_VALUE
            val items = data?.getStringArrayListExtra("items") ?: ArrayList()

            filter(IntRange(minKk, maxKk), IntRange(timeMin, timeMax), items)
        }
    }

    companion object {
        fun newInstance() = FragmentHome()
    }
}

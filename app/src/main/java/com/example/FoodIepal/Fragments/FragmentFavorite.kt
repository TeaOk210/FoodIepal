package com.example.FoodIepal.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Activities.FullScreen
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.Entities.RecipeType
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.VIew.RecipeAdapter
import com.example.FoodIepal.VIew.RecipeViewModel
import com.example.FoodIepal.databinding.FragmentFavoriteBinding

class FragmentFavorite : Fragment(), RecipeAdapter.OnItemClickListener {
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentFavoriteBinding
    private val dataModel: DataModel by activityViewModels()
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        dataModel.desk.observe(viewLifecycleOwner) {

            val name = dataModel.name.value.toString()
            val prep = dataModel.prep.value.toString()
            val Kkal = dataModel.Kkal.value!!
            val time = dataModel.time.value!!
            val image = dataModel.image.value!!
            val items = dataModel.setItems.value.toString()
            val desk = dataModel.desk.value.toString()
            val login = sessionManager.getUserName()

            viewModel.insertRecipe(
                Recipe(
                    null, name, prep, desk, items, Kkal, time, image, RecipeType.CUSTOM, login
                )
            )
        }

        binding = FragmentFavoriteBinding.inflate(inflater)

        setUpAdapter()

        binding.toolbar2.apply {
            title = "Избранное"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.custom_toolbar_favorite)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        populateList()
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
        viewModel.allFavoriteRecipes.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.filter(list as ArrayList<Recipe>)
            }
        }
    }

    private fun setUpAdapter() {
        adapter = RecipeAdapter(this)
        binding.FavoriteList.adapter = adapter
        binding.FavoriteList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentFavorite()
    }
}
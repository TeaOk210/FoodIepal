package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.RecipeAdapter
import com.example.FoodIepal.RecipeItem
import com.example.FoodIepal.Utils.FatSecretGet
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding
import org.json.JSONObject


@Suppress("DEPRECATION")
class FragmentHome : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding : FragmentHomeMenuBinding
    lateinit var mFatSecretGet: FatSecretGet
    var ItemCount: Int = 0
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenuBinding.inflate(inflater)
        SetUpAdapter()
        binding.button.setOnClickListener{
            ItemCount++
            populateList()
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    fun populateList() {
        val i: Int = ItemCount
        val name = "Recipe name"
        val text = "opisanie recepta, it is recipe number $i"
        val time = i *5
        val Kkal = i * 100
        val recipeItem = RecipeItem(name = name, text = text, time = time, Kkal = Kkal, RecipeImageResId = R.drawable.food)
        RecipeItemList.add(recipeItem)
        }

    private fun getFood(id: Long) {
        AsyncTask<String, String, String>().execute() {
            val foodGet: JSONObject? = mFatSecretGet.getFood(id)
            try {
                if(foodGet != null) {
                    val food_name: String = foodGet.
                }
            }
        }
    }

    fun SetUpAdapter(){
        adapter = RecipeAdapter(requireActivity(), RecipeItemList )
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }


    companion object {
        fun newInstance() = FragmentHome()
    }
}
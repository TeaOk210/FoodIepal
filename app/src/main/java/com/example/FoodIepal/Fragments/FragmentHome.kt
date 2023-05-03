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
    private lateinit var FatSecretGet: FatSecretGet
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
                val img = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Image_parh))
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

//    private fun getFood(id: Long) {
//        object : AsyncTask<String, String, String>() {
//            override fun doInBackground(vararg arg0: String): String {
//                val foodGet = FatSecretGet.getFood(id)
//                try {
//                    if (foodGet != null) {
//                        val food_name = foodGet.getString("food_name")
//                        val servings = foodGet.getJSONObject("servings")
//                        val serving = servings.getJSONObject("serving")
//                        val calories = serving.getString("calories")
//                        val carbohydrate = serving.getString("carbohydrate")
//                        val protein = serving.getString("protein")
//                        val fat = serving.getString("fat")
//                        val serving_description = serving.getString("serving_description")
//                        Log.e("serving_description", serving_description)
//                        /**
//                         * Displays results in the LogCat
//                         */
//                        Log.e("food_name", food_name)
//                        Log.e("calories", calories)
//                        Log.e("carbohydrate", carbohydrate)
//                        Log.e("protein", protein)
//                        Log.e("fat", fat)
//                    }
//                } catch (exception: JSONException) {
//                    return "Error"
//                }
//                return ""
//            }
//            override fun onPostExecute(result: String) {
//                super.onPostExecute(result)
//                if (result == "Error")
//                    Toast.makeText(activity, "No Items Containing Your Search", Toast.LENGTH_SHORT).show()
//                mCallbacks.fromFragment()
//            }
//        }.execute()
//    }
}

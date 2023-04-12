package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.MainMenu
import com.example.FoodIepal.R
import com.example.FoodIepal.RecipeAdapter
import com.example.FoodIepal.RecipeItem
import com.example.FoodIepal.Utils.FatSecretGet
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding
import org.json.JSONException
import org.json.JSONObject


@Suppress("DEPRECATION")
class FragmentHome : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentHomeMenuBinding
    var ItemCount: Int = 0
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenuBinding.inflate(inflater)
        SetUpAdapter()
        binding.button.setOnClickListener {
            ItemCount++
            populateList()
            adapter.notifyDataSetChanged()
            getFood(ItemCount.toLong() + 1)
        }
        return binding.root
    }

    fun populateList() {
        val i: Int = ItemCount
        val name = "Recipe name"
        val text = "opisanie recepta, it is recipe number $i"
        val time = i * 5
        val Kkal = i * 100
        val recipeItem = RecipeItem(
            name = name,
            text = text,
            time = time,
            Kkal = Kkal,
            RecipeImageResId = R.drawable.food
        )
        RecipeItemList.add(recipeItem)
    }


    fun SetUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList)
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentHome()
    }

    private fun getFood(id: Long) {
        class FoodAsyncTask(val mFatSecretGet: FatSecretGet) :
            AsyncTask<String, String, String>() {
            override fun doInBackground(vararg arg0: String): String {
                val foodGet: JSONObject? = mFatSecretGet.getFood(id);
                try {
                    if (foodGet != null) {
                        val food_name: String = foodGet.getString("food_name");
                        val servings: JSONObject = foodGet.getJSONObject("servings");
                        val serving: JSONObject = servings.getJSONObject("serving");
                        val calories: String = serving.getString("calories");
                        val carbohydrate: String = serving.getString("carbohydrate");
                        val protein: String = serving.getString("protein");
                        val fat: String = serving.getString("fat");
                        val serving_description: String = serving.getString("serving_description");

                        Log.e("serving_description", serving_description);
                        Log.e("food_name", food_name);
                        Log.e("calories", calories);
                        Log.e("carbohydrate", carbohydrate);
                        Log.e("protein", protein);
                        Log.e("fat", fat);
                    }

                } catch (exception: JSONException) {
                    return "Error";
                }
                return ""
            }
            override fun onPostExecute(result: String) {
                super.onPostExecute(result);
                if (result == "Error") {
                    Toast.makeText(activity, "No Items Containing Your Search", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute()
    }
}

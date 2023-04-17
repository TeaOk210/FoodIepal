package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding


@Suppress("DEPRECATION")
class FragmentHome : Fragment() {

    private val RecipeItemList = ArrayList<RecipeItem>()
    private lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentHomeMenuBinding
    private var ItemCount: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenuBinding.inflate(inflater)

        SetUpAdapter() // add RV

        // add items for Recycler View
        binding.button.setOnClickListener {
            ItemCount++
            populateList()
            adapter.notifyDataSetChanged()
//            getFood(ItemCount.toLong() + 1)
        }
        return binding.root
    }

    private fun populateList() {
        val i: Int = ItemCount
        val name = "Recipe name"
        val text = "opisanie recepta, it is recipe number $i"
        val time = i * 5
        val Kkal = i * 100
        val recipeItem = RecipeItem(
            name = name,
            text = text,
            time = time.toString(),
            Kkal = Kkal.toString(),
            RecipeImageResId = R.drawable.food
        )
        RecipeItemList.add(recipeItem)
    }

    private fun SetUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList)
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentHome()
    }
/*
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
*/
}

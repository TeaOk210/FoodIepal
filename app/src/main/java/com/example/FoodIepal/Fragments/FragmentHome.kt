package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.FullScreen
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class FragmentHome : Fragment(){
    private val RecipeItemList = ArrayList<RecipeItem>()
    private var filteredList = ArrayList<RecipeItem>()
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
                        dataModel.items.observe(activity as LifecycleOwner) {items ->
                            Filter(IntRange(minKk, maxkK), IntRange(minTt, maxTt), items)
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
                Search(s.toString())
            }
        })
        binding.toolbarMenu.title = "Главная"
        dbManager.open()
        SetUpAdapter()
        populateList()
        return binding.root
    }

        fun Filter(kkalRange: IntRange, timeRange: IntRange, items: Array<String>) {
        filteredList.clear()

        for (item in RecipeItemList) {
            for (recipeItem in items) {
                if (item.Kkal in kkalRange && item.time in timeRange && recipeItem in item.recipeItems) {
                    filteredList.add(item)
                }
            }
        }
        adapter.filter(filteredList)
    }
    companion object {
        fun newInstance() = FragmentHome()
    }
    @SuppressLint("DefaultLocale")
    private fun Search(text: String) {
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

        val images = arrayOf(
            R.drawable.figna,
            R.drawable.waf,
            R.drawable.blin,
            R.drawable.max
        ).map { resourceId -> getBytesFromResource(resourceId) }

        var imageIndex = 0
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Description))
                val items = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_Items))
                val time = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Calories))

                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImage = images[imageIndex],
                    recipeItems = items
                )

                imageIndex = (imageIndex + 1) % images.size

                RecipeItemList.add(recipeItem)
                filteredList.add(recipeItem)

            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    private fun SetUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList, object : RecipeAdapter.OnItemClickListener{
            @SuppressLint("Range")
            override fun onItemClick(data: RecipeItem) {
                val intent = Intent(requireContext(), FullScreen::class.java)

                intent.putExtra("Kkal", data.Kkal)
                intent.putExtra("time", data.time)
                intent.putExtra("name", data.name)
                intent.putExtra("text", data.text)
                intent.putExtra("image", data.RecipeImage)
                intent.putExtra("items", data.recipeItems)

                startActivity(intent)
            }
        })

        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun getBytesFromResource(resourceId: Int): ByteArray {
        val inputStream = requireContext().resources.openRawResource(resourceId)
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(4096)
        var bytesRead: Int

        while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
            outputStream.write(buffer, 0, bytesRead)
        }

        return outputStream.toByteArray()
    }
}

package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.FullScreen
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.databinding.FragmentFavoriteBinding

class FragmentFavorite : Fragment() {
    var RecipeItemList = ArrayList<RecipeItem>()
    private lateinit var sessionManager: SessionManager
    lateinit var adapter: RecipeAdapter
    lateinit var binding: FragmentFavoriteBinding
    lateinit var dbManager: DBManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireActivity())
        dbManager.open()

        sessionManager = SessionManager(requireContext())

        binding = FragmentFavoriteBinding.inflate(inflater)

        populateList()
        setUpAdapter()

        binding.toolbar2.title = "Избранное"
        binding.toolbar2.inflateMenu(R.menu.custom_toolbsr_favorite)

        return binding.root
    }

    @SuppressLint("Range")
    private fun populateList() {
        val cursor = dbManager.fetchFavorite(sessionManager.getUserName())

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Description))
                val items = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_Items))
                val time = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Calories))
                val bytesImage = cursor.getBlob(cursor.getColumnIndex(DataBaseHalper.Image_parh))

                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImage = bytesImage,
                    recipeItems = items
                )

                RecipeItemList.add(recipeItem)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }


    fun setUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList, object : RecipeAdapter.OnItemClickListener{
            override fun onItemClick(data: RecipeItem){
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
        binding.FavoriteList.adapter = adapter
        binding.FavoriteList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentFavorite()
    }
}
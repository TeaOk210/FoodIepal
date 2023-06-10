package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.FullScreen
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataBaseHalper
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.Utils.RecipeAdapter
import com.example.FoodIepal.Utils.RecipeItem
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.FragmentFavoriteBinding

class FragmentFavorite : Fragment() {
    private var RecipeItemList = ArrayList<RecipeItem>()
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var dbManager: DBManager
    private val dataModel: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireActivity())
        dbManager.open()

        sessionManager = SessionManager(requireContext())

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

    @SuppressLint("Range", "NotifyDataSetChanged")
    fun populateList() {
        RecipeItemList.clear()

        val login= sessionManager.getUserName()

        val cursorFV = dbManager.fetchFavorite(login)
        val cursorPR =dbManager.fetchPersonal(login)

        if (cursorPR.moveToFirst()) {
            do {
                val name = cursorPR.getString(cursorPR.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursorPR.getString(cursorPR.getColumnIndex(DataBaseHalper.Description))
                val items = cursorPR.getString(cursorPR.getColumnIndex(DataBaseHalper.Recipe_Items))
                val preparation = cursorPR.getString(cursorPR.getColumnIndex(DataBaseHalper.Preparation))
                val time = cursorPR.getInt(cursorPR.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursorPR.getInt(cursorPR.getColumnIndex(DataBaseHalper.Calories))
                val bytesImage = cursorPR.getBlob(cursorPR.getColumnIndex(DataBaseHalper.Image_parh))

                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImage = bytesImage,
                    recipeItems = items,
                    Preparation = preparation
                )

                RecipeItemList.add(recipeItem)
            } while (cursorPR.moveToNext())
        }

        if (cursorFV.moveToFirst()) {
            do {
                val name = cursorFV.getString(cursorFV.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursorFV.getString(cursorFV.getColumnIndex(DataBaseHalper.Description))
                val items = cursorFV.getString(cursorFV.getColumnIndex(DataBaseHalper.Recipe_Items))
                val preparation = cursorFV.getString(cursorFV.getColumnIndex(DataBaseHalper.Preparation))
                val time = cursorFV.getInt(cursorFV.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursorFV.getInt(cursorFV.getColumnIndex(DataBaseHalper.Calories))
                val bytesImage = cursorFV.getBlob(cursorFV.getColumnIndex(DataBaseHalper.Image_parh))

                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImage = bytesImage,
                    recipeItems = items,
                    Preparation = preparation
                )

                RecipeItemList.add(recipeItem)
            } while (cursorFV.moveToNext())
        }

        adapter.notifyDataSetChanged()
        cursorFV.close()
        cursorPR.close()
    }

    private fun setUpAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList, object : RecipeAdapter.OnItemClickListener{
            override fun onItemClick(data: RecipeItem){
                val intent = Intent(requireContext(), FullScreen::class.java)

                intent.putExtra("Kkal", data.Kkal)
                intent.putExtra("time", data.time)
                intent.putExtra("name", data.name)
                intent.putExtra("text", data.text)
                intent.putExtra("image", data.RecipeImage)
                intent.putExtra("items", data.recipeItems)
                intent.putExtra("preparation", data.Preparation)

                startActivityForResult(intent, 1)
            }
        })
        binding.FavoriteList.adapter = adapter
        binding.FavoriteList.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentFavorite()
    }
}
package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment() {
    private val ItemList = ArrayList<ItemItem>()
    private lateinit var adapter: ItemAdapter
    lateinit var binding: FragmentBascetBinding
    lateinit var dbManager: DBManager

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireContext())
        binding = FragmentBascetBinding.inflate(inflater)
        dbManager.open()
        populateList()
        setUpAdapter()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "Range")
    fun populateList(){
        val cursor = dbManager.fetchBasket()
        if (cursor.moveToFirst()){
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_name))
                val dose = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_Dose))

                val basketItem = ItemItem(
                    name = name,
                    dose = dose
                )
                ItemList.add(basketItem)
            } while (cursor.moveToFirst())
        }
        cursor.close()
    }

    fun setUpAdapter() {
        adapter = ItemAdapter(requireActivity(), ItemList)
        binding.Bascet.adapter = adapter
        binding.Bascet.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentBascet()
    }
}
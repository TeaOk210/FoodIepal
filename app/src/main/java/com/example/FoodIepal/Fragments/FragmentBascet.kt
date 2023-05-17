package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.R
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
        getToolbar()
        return binding.root
    }

    private fun getToolbar(){
        binding.toolbar3.title = "Корзина"
        binding.toolbar3.inflateMenu(R.menu.cutom_toolbar_basket)
    }

    @SuppressLint("Range")
    fun populateList(){
        val cursor = dbManager.fetchBasket()
        if (cursor.moveToFirst()){
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_name))
                val dose = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_Dose))

                val basketItem = ItemItem(
                    ItemName = name,
                    ItemDose = dose
                )
                ItemList.add(basketItem)
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    fun setUpAdapter() {
        adapter = ItemAdapter(requireActivity(), ItemList, object : ItemAdapter.onDeleteListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDelete(data: ItemItem) {
                Toast.makeText(requireContext(), "УДАЛЕНО!", Toast.LENGTH_SHORT).show()
                dbManager.deleteBasket(data.ItemName)
                populateList()
            }
        })
        binding.Bascet.adapter = adapter
        binding.Bascet.layoutManager = LinearLayoutManager(requireActivity())
    }

    companion object {
        fun newInstance() = FragmentBascet()
    }
}
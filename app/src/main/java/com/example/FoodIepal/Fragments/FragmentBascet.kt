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
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireContext())
        dbManager.open()

        binding = FragmentBascetBinding.inflate(inflater)

        sessionManager = SessionManager(requireContext())

        setUpAdapter()
        getToolbar()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        populateList()
    }

    private fun getToolbar(){
        binding.toolbar3.apply {
            title = "Корзина"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.cutom_toolbar_basket)
        }
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    fun populateList(){
        ItemList.clear()
        val cursor = dbManager.fetchBasket(sessionManager.getUserName())
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
        adapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun setUpAdapter() {
        adapter = ItemAdapter(requireActivity(), ItemList, object : ItemAdapter.OnDeleteListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDelete(data: ItemItem) {
                Toast.makeText(requireContext(), "Удалено!", Toast.LENGTH_SHORT).show()
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
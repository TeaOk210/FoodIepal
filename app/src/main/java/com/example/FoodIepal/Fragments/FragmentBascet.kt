@file:Suppress("UNREACHABLE_CODE")

package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.FoodIepal.Entities.Bascet
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.VIew.BascetAdapter
import com.example.FoodIepal.VIew.BascetViewModel
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment(), BascetAdapter.OnDeleteListener {
    private lateinit var adapter: BascetAdapter
    lateinit var binding: FragmentBascetBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: BascetViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        )[BascetViewModel::class.java]

        adapter = BascetAdapter(this)
        observeEvents()
        getToolbar()

        return binding.root
    }

    private fun getToolbar(){
        binding.toolbar3.apply {
            title = "Корзина"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.cutom_toolbar_basket)
        }
    }

//    @SuppressLint("Range", "NotifyDataSetChanged")
//    fun populateList(){
//        ItemList.clear()
//        val cursor = dbManager.fetchBasket(sessionManager.getUserName())
//        if (cursor.moveToFirst()){
//            do {
//                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_name))
//                val dose = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Item_Dose))
//
//                val basketItem = ItemItem(
//                    ItemName = name,
//                    ItemDose = dose
//                )
//                ItemList.add(basketItem)
//            } while (cursor.moveToNext())
//        }
//        adapter.notifyDataSetChanged()
//        cursor.close()
//    }

    private fun observeEvents() {
        viewModel.allBascet.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    override fun onDelete(data: Bascet) {
        Toast.makeText(requireContext(), "Удалено!", Toast.LENGTH_SHORT).show()
        viewModel.deleteBascet(data.name)
    }

    companion object {
        fun newInstance() = FragmentBascet()
    }
}
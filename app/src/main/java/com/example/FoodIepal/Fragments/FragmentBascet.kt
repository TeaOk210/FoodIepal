package com.example.FoodIepal.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Utils.ItemAdapter
import com.example.FoodIepal.Utils.ItemItem
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment() {
    private val ItemList = ArrayList<ItemItem>()
    private lateinit var adapter: ItemAdapter
    lateinit var binding: FragmentBascetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)
        setUpAdapter()
        binding.ItemAdd.setOnClickListener {
            val DialogFragment = DialogItemAdd()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            DialogFragment.show(transaction, "Add new Item")
        }
        return binding.root
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
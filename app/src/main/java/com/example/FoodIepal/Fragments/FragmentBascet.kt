package com.example.FoodIepal.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.Utils.ItemAdapter
import com.example.FoodIepal.Utils.ItemItem
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment() {
    private val ItemList = ArrayList<ItemItem>()
    private lateinit var adapter: ItemAdapter
    lateinit var binding: FragmentBascetBinding
    private val dataModel: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)
        dataModel.Name.observe(activity as LifecycleOwner) { ItemName ->
            dataModel.Dose.observe(activity as LifecycleOwner) { ItemDose ->
                populateList(ItemName, ItemDose)
            }
        }
        setUpAdapter()
        return binding.root
    }
    private fun populateList(Name: String, Dose: String) {
        val item = ItemItem(
            name = Name,
            dose = Dose
        )
        ItemList.add(item)
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
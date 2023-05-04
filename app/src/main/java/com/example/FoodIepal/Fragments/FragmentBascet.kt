package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)
        activity?.let { fragmentActivity ->
            dataModel.Name.observe(fragmentActivity, Observer { ItemName ->
                dataModel.Dose.observe(fragmentActivity, Observer { ItemDose ->
                    populateList(ItemName, ItemDose)
                })
            })
        }
        setUpAdapter()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    fun populateList(Name: String, Dose: String){
        val item = ItemItem(
            name = Name,
            dose = Dose
        )
        ItemList.add(item)
        adapter.notifyDataSetChanged()
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
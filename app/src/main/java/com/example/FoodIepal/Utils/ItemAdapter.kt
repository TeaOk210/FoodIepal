package com.example.FoodIepal.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.databinding.ItemItemLayoutBinding

class ItemAdapter(private val context: Context, private var ItemList:ArrayList<ItemItem>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val ItemItem = ItemList[position]
        holder.bind(ItemItem)
    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    class ItemViewHolder(private val binding: ItemItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ItemItem: ItemItem) {
            binding.TextName.text = ItemItem.name
            binding.TextDose.text = ItemItem.dose
        }
    }
}
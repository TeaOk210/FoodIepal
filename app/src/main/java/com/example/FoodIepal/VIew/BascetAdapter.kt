package com.example.FoodIepal.VIew

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.databinding.ItemItemLayoutBinding


class BascetAdapter(
    private val onDeleteListener: OnDeleteListener
) : RecyclerView.Adapter<BascetAdapter.BascetViewHolder>() {

    private val allBascet = ArrayList<Basket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BascetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemItemLayoutBinding.inflate(inflater, parent, false)
        return BascetViewHolder(binding, onDeleteListener)
    }

    override fun getItemCount(): Int {
        return allBascet.size
    }

    override fun onBindViewHolder(holder: BascetViewHolder, position: Int) {
        val bascetItem = allBascet[position]
        holder.bind(bascetItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Basket>) {
        allBascet.clear()

        allBascet.addAll(newList)

        notifyDataSetChanged()
    }

    class BascetViewHolder(
        private val binding: ItemItemLayoutBinding,
        private val listener: OnDeleteListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Basket) {
            binding.TextName.text = item.name
            binding.TextDose.text = item.dose

            binding.deleteButton.setOnClickListener {
                listener.onDelete(item)
            }
        }
    }

    interface OnDeleteListener {
        fun onDelete(data: Basket)
    }
}
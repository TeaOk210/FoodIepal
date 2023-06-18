package com.example.FoodIepal.VIew

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.databinding.ItemItemLayoutBinding


@SuppressLint("NotifyDataSetChanged")
class BascetAdapter(
    private val onDeleteListener: OnDeleteListener
) : RecyclerView.Adapter<BascetAdapter.BascetViewHolder>() {

    private var allBascet = ArrayList<Basket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BascetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemItemLayoutBinding.inflate(inflater, parent, false)
        return BascetViewHolder(binding, onDeleteListener)
    }

    override fun getItemCount(): Int {
        return allBascet.size
    }

    fun updateList(newLIst: ArrayList<Basket>) {
        allBascet = newLIst
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BascetViewHolder, position: Int) {
        val bascetItem = allBascet[position]

        holder.bind(bascetItem)
    }

    class BascetViewHolder(
        private val binding: ItemItemLayoutBinding,
        private val listener: OnDeleteListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Basket) {
            binding.apply {
                TextName.text = item.name
                TextDose.text = item.dose

                deleteButton.setOnClickListener {
                    listener.onDelete(item)
                }
            }
        }
    }

    interface OnDeleteListener {
        fun onDelete(data: Basket)
    }
}
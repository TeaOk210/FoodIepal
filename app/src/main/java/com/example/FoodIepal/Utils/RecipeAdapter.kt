package com.example.FoodIepal.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.databinding.RecipeItemLayoutBinding

class RecipeAdapter(private val context: Context, private var recipeItemList:ArrayList<RecipeItem>, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return RecipeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val RecipeItem = recipeItemList[position]
        holder.bind(RecipeItem)
        holder.itemView.setOnClickListener{
            listener.onItemClick(RecipeItem)
        }
    }

    fun filter(filterList: ArrayList<RecipeItem>) {
        recipeItemList = filterList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeItemList.size
    }

    class RecipeViewHolder(private val binding: RecipeItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(RecipeItem: RecipeItem) {
            binding.RecipeName.text = RecipeItem.name
            binding.RecipeText.text = RecipeItem.text
            binding.RecipeTime.text = RecipeItem.time.toString()
            binding.RecipeKkal.text = RecipeItem.Kkal.toString()
            binding.RecipePhoto.setImageResource(RecipeItem.RecipeImage)

        }
    }
    interface OnItemClickListener {
        fun onItemClick(data: RecipeItem)
    }
}
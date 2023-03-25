package com.example.FoodIepal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.databinding.RecipeItemLayoutBinding

class RecipeAdapter(private val context: Context, private val recipeItemList:MutableList<RecipeItem>)
    : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return RecipeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val RecipeItem = recipeItemList[position]
        holder.bind(RecipeItem)
    }

    override fun getItemCount(): Int {
        return recipeItemList.size
    }

    class RecipeViewHolder(recipeItemLayoutBinding: RecipeItemLayoutBinding)
        : RecyclerView.ViewHolder(recipeItemLayoutBinding.root) {
        private lateinit var binding : RecipeItemLayoutBinding

        fun bind(RecipeItem: RecipeItem) {
            binding.RecipeName.text = RecipeItem.text
            binding.RecipeName.text = RecipeItem.name
            binding.RecipeTime.text = RecipeItem.time.toString()
            binding.RecipeKkal.text = RecipeItem.Kkal.toString()
        }
    }
}
package com.example.FoodIepal.VIew

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.databinding.RecipeItemLayoutBinding

@SuppressLint("NotifyDataSetChanged")
class RecipeAdapter(
    private val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var allRecipe = ArrayList<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeItem = allRecipe[position]
        holder.bind(recipeItem)

        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(recipeItem)
        }
    }

    fun filter(filterList: ArrayList<Recipe>?) {
        allRecipe = filterList ?: arrayListOf()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allRecipe.size
    }

    class RecipeViewHolder(private val binding: RecipeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.RecipeName.text = item.recipeName

            binding.RecipeText.text = item.description

            binding.RecipeTime.text = item.cookTime.toString()
            binding.RecipeKkal.text = item.calories.toString()

            val bitmap = BitmapFactory.decodeByteArray(
                item.imagePath,
                0,
                item.imagePath.size
            )
            binding.RecipePhoto.setImageBitmap(bitmap)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: Recipe)
    }
}
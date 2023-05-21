package com.example.FoodIepal.Utils

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
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

            val text = RecipeItem.text

            // Wait for the layout to be drawn and the width of RecipeText to be available
            binding.RecipeText.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.RecipeText.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    val displayText = getTrimmedTextToFitScreen(text)
                    binding.RecipeText.text = displayText
                }
            })

            binding.RecipeTime.text = RecipeItem.time.toString()
            binding.RecipeKkal.text = RecipeItem.Kkal.toString()

            val items: String = RecipeItem.recipeItems

            val bitmap = BitmapFactory.decodeByteArray(RecipeItem.RecipeImage, 0, RecipeItem.RecipeImage.size)
            binding.RecipePhoto.setImageBitmap(bitmap)
        }

        private fun getTrimmedTextToFitScreen(text: String): String {
            val textViewWidth = binding.RecipeText.width
            val paint = binding.RecipeText.paint
            val ellipsis = "..."

            var trimmedText = text
            val ellipsisWidth = paint.measureText(ellipsis)

            while (paint.measureText(trimmedText) > textViewWidth - ellipsisWidth) {
                trimmedText = trimmedText.dropLast(1)
            }

            if (trimmedText.length < text.length) {
                trimmedText += ellipsis
            }

            return trimmedText
        }

    }

    interface OnItemClickListener {
        fun onItemClick(data: RecipeItem)
    }
}
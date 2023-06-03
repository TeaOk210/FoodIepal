package com.example.FoodIepal.Utils


data class RecipeItem(val name: String, val text: String, val time: Int, val Kkal: Int, val RecipeImage: ByteArray, val recipeItems: String, val Preparation: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecipeItem

        if (name != other.name) return false
        if (text != other.text) return false
        if (time != other.time) return false
        if (Kkal != other.Kkal) return false
        if (!RecipeImage.contentEquals(other.RecipeImage)) return false
        if (recipeItems != other.recipeItems) return false
        if (Preparation != other.Preparation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + time
        result = 31 * result + Kkal
        result = 31 * result + RecipeImage.contentHashCode()
        result = 31 * result + recipeItems.hashCode()
        result = 31 * result + Preparation.hashCode()
        return result
    }
}
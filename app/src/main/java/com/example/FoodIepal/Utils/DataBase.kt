package com.example.FoodIepal.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.Entities.Recipe
import com.example.FoodIepal.Entities.RecipeType
import com.example.FoodIepal.Entities.User
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader

@Database(
    version = 2,
    entities = [
        Basket::class,
        User::class,
        Recipe::class
    ]
)
abstract class DataBase : RoomDatabase() {

    abstract fun getBascetDao(): BasketDao
    abstract fun getUserDao(): UserDao
    abstract fun getRecipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        private const val DB_NAME = "new_test_database.db"

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
//                    .addCallback()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val context: Context) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val recipeDao = INSTANCE?.getRecipeDao()
                val sessionManager = SessionManager(context)

                val fileContent = readRecipesFromFile(context)
                val recipes = parseRecipeData(fileContent, context)

                for (recipe in recipes) {
                    val recipeEntity = Recipe(
                        recipe.id,
                        recipe.recipeName,
                        recipe.description,
                        recipe.recipeItems,
                        recipe.preparation,
                        recipe.calories,
                        recipe.cookTime,
                        recipe.imagePath,
                        recipe.recipeType,
                        sessionManager.getUserName()
                    )
                    recipeDao?.insertRecipe(recipeEntity)
                }
            }

            private fun readRecipesFromFile(context: Context): String {
                val fileInputStream = context.assets.open("recipes.txt")
                val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append("\n")
                }
                bufferedReader.close()
                return stringBuilder.toString()
            }

            private fun parseRecipeData(fileContent: String, context: Context): List<Recipe> {

                val images = arrayOf(
                    R.drawable.keks,
                    R.drawable.okroshca,
                    R.drawable.laph_krivet,
                    R.drawable.kart_grib,
                    R.drawable.hok_keks,
                    R.drawable.sir_lepeshka,
                    R.drawable.grechka_ovoshi,
                    R.drawable.ydon_kanada,
                    R.drawable.grek_salt,
                    R.drawable.ris_blin,
                    R.drawable.ban_ovs_alad,
                    R.drawable.kotl_kur,
                    R.drawable.lavash_tvorog,
                    R.drawable.sirniki,
                    R.drawable.kur_salt,
                    R.drawable.chis_ris,
                    R.drawable.salt_kriv,
                    R.drawable.bulg_plov_kur,
                    R.drawable.pech_tvorohn,
                    R.drawable.ovosh_ragu
                ).map { resourceId -> getBytesFromResource(resourceId, context) }

                val recipeList = mutableListOf<Recipe>()
                val recipeEntries = fileContent.trim().split("\n\n")

                var index = 0
                for (entry in recipeEntries) {
                    val lines = entry.split("\n")
                    if (lines.size >= 7) {

                        index = (index + 1) % images.size
                        val name = lines[0]
                        val description = lines[1]
                        val ingredients = lines.subList(2, lines.size - 3).joinToString("\n")
                        val instructions = lines[lines.size - 3]
                        val time = lines[lines.size - 2].toInt()
                        val Kkal = lines[lines.size - 1].toInt()

                        val recipe = Recipe(
                            null,
                            name,
                            description,
                            instructions,
                            ingredients,
                            Kkal,
                            time,
                            images[index],
                            RecipeType.DEFAULT,
                            ""
                        )
                        recipeList.add(recipe)
                    }
                }
                return recipeList
            }

            private fun getBytesFromResource(resourceId: Int, context: Context): ByteArray {
                val inputStream = context.resources.openRawResource(resourceId)
                val outputStream = ByteArrayOutputStream()
                val buffer = ByteArray(4096)
                var bytesRead: Int

                while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                return outputStream.toByteArray()
            }
        }
    }
}
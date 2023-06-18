package com.example.FoodIepal.Activities

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.FoodIepal.Entities.RecipeType
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.Utils.RecipeRepository
import com.example.FoodIepal.databinding.ActivityFullScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class FullScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    private lateinit var sessionManager: SessionManager

    private lateinit var repository: RecipeRepository

    private lateinit var name: String
    private lateinit var text: String
    private lateinit var items: String
    private lateinit var preparation: String
    private lateinit var login: String

    private var time by Delegates.notNull<Int>()
    private var Kkal by Delegates.notNull<Int>()
    private var image by Delegates.notNull<ByteArray>()
    private lateinit var type: RecipeType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        repository = RecipeRepository(DataBase.getDatabase(application).getRecipeDao())

        binding = ActivityFullScreenBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)
        getData()
        setContentView(binding.root)
    }

    private fun getData() {
        intent.apply {
            name = getStringExtra("name").toString()
            text = getStringExtra("text").toString()
            items = getStringExtra("items").toString()
            preparation = getStringExtra("preparation").toString()

            time = getIntExtra("time", 0)
            Kkal = getIntExtra("Kkal", 0)

            image = getByteArrayExtra("image") ?: byteArrayOf()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val result = repository.getRecipeType(name, login)
            withContext(Dispatchers.Main) {
                type = result!!
            }
        }

        login = sessionManager.getUserName()

        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)

        binding.apply {
            KkalView.text = Kkal.toString()
            TimeView.text = time.toString()
            DeskView.text = preparation
            ItemsView.text = items

            ImageView.setImageBitmap(bitmap)

            toolbarTitle.apply {
                text = name
                this!!.isSelected = true
            }
        }

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_button)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)

        val item = menu?.findItem(R.id.addToFavorite)
        if (checkFavorite(type)) {
            item?.setIcon(R.drawable.baseline_star_24)
        } else {
            item?.setIcon(R.drawable.baseline_star_border_24)
        }
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.addToFavorite -> {
                if (checkFavorite(type)) {
                    item.setIcon(R.drawable.baseline_star_border_24)

                    lifecycleScope.launch(Dispatchers.IO) {
                        repository.updateRecipeType(
                            name,
                            RecipeType.DEFAULT
                        )
                    }

                    Toast.makeText(this, "Удалено из избранного", Toast.LENGTH_SHORT).show()
                } else {
                    item.setIcon(R.drawable.baseline_star_24)

                    lifecycleScope.launch(Dispatchers.IO) {
                        repository.updateRecipeType(
                            name,
                            RecipeType.FAVORITE
                        )
                    }

                    Toast.makeText(this, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun checkFavorite(type: RecipeType): Boolean {
        return type == RecipeType.FAVORITE
    }
}
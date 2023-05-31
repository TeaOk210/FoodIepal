package com.example.FoodIepal

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataBaseHalper
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.ActivityFullScreenBinding
import kotlin.properties.Delegates

class FullScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var dbManager: DBManager

    lateinit var name: String
    lateinit var text: String
    lateinit var items: String
    lateinit var preparation: String
    lateinit var login: String

    var time by Delegates.notNull<Int>()
    var Kkal by Delegates.notNull<Int>()
    var image by Delegates.notNull<ByteArray>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        dbManager = DBManager(this)

        binding = ActivityFullScreenBinding.inflate(layoutInflater)

        dbManager.open()

        setSupportActionBar(binding.toolbar)
        getData()
        setContentView(binding.root)
    }

    private fun getData(){
        name = intent.getStringExtra("name").toString()
        text = intent.getStringExtra("text").toString()
        items = intent.getStringExtra("items").toString()
        preparation = intent.getStringExtra("preparation").toString()

        time = intent.getIntExtra("time", 0)
        Kkal = intent.getIntExtra("Kkal", 0)

        image = (intent.getByteArrayExtra("image") ?: byteArrayOf())

        login = sessionManager.getUserName()

        binding.KkalView.text = Kkal.toString()
        binding.TimeView.text = time.toString()

        binding.DeskView.text = preparation

        binding.ItemsView.text = items

        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
        binding.ImageView.setImageBitmap(bitmap)

        supportActionBar?.apply {
            title = name
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_button)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)
        val item = menu?.findItem(R.id.addToFavorite)
        if (checkFavorite()){
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
                if (checkFavorite()) {
                    item.setIcon(R.drawable.baseline_star_border_24)
                    dbManager.deleteRecipe(name)
                    Toast.makeText(this, "Удалено из избранного", Toast.LENGTH_SHORT).show()
                } else {
                    item.setIcon(R.drawable.baseline_star_24)
                    dbManager.insertFavorite(name, text, items, Kkal, time, image, login, preparation)
                    Toast.makeText(this, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                }

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Range")
    private fun checkFavorite(): Boolean {
        val cursor = dbManager.fetchFavorite(login)

        if (cursor.moveToFirst()) {
            do {
                val recipeName = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_NAme))
                if (recipeName.equals(name, ignoreCase = true)) {
                    return true
                }
            } while (cursor.moveToNext())
        }
        return false
    }
}
package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.FullScreen
import com.example.FoodIepal.R
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.databinding.FragmentHomeMenuBinding
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class FragmentHome : Fragment(){
    private val RecipeItemList = ArrayList<RecipeItem>()
    private var filteredList = ArrayList<RecipeItem>()
    private lateinit var dbManager: DBManager
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentHomeMenuBinding
    private val dataModel: DataModel by activityViewModels()
    private lateinit var sessionManager: SessionManager
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbManager = DBManager(requireActivity())

        sessionManager = SessionManager(requireContext())

        binding = FragmentHomeMenuBinding.inflate(inflater)

        dbManager.open()

        setAdapter()
        populateList()

        dataModel.minKk.observe(viewLifecycleOwner) { minKk ->
            dataModel.maxKk.observe(viewLifecycleOwner) { maxkK ->
                dataModel.minTt.observe(viewLifecycleOwner) { minTt ->
                    dataModel.maxTt.observe(viewLifecycleOwner) { maxTt ->
                        dataModel.items.observe(viewLifecycleOwner) { items ->
                            filter(IntRange(minKk, maxkK), IntRange(minTt, maxTt), items)
                        }
                    }
                }
            }
        }

            binding.SearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                search(s.toString())
            }
        })

        binding.toolbarMenu.apply {
            title = "Главная"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.custom_toolvar_menu)
        }
        return binding.root
    }

    private fun filter(kkalRange: IntRange, timeRange: IntRange, items: ArrayList<String>) {
        val filteredSet = mutableSetOf<RecipeItem>()
        for (item in RecipeItemList) {
            if (items.isEmpty()) {
                if (item.Kkal in kkalRange && item.time in timeRange) {
                    filteredSet.add(item)
                }
            } else {
                var containsAllItems = true
                for (recipeItem in items) {
                    if (recipeItem !in item.recipeItems) {
                        containsAllItems = false
                        break
                    }
                }
                if (containsAllItems && item.Kkal in kkalRange && item.time in timeRange) {
                    filteredSet.add(item)
                }
            }
        }

        val filteredList = ArrayList(filteredSet)
        adapter.filter(filteredList)
    }

    @SuppressLint("DefaultLocale")
    private fun search(text: String) {
        val searchedSet: MutableSet<RecipeItem> = mutableSetOf()

        for (item in filteredList) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                searchedSet.add(item)
            }
        }

        val searchedList: ArrayList<RecipeItem> = ArrayList(searchedSet)
        adapter.filter(searchedList)

        if (searchedList.isEmpty()) {
            Toast.makeText(requireActivity(), "Такого нет...", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    private fun populateList() {
        val cursor = dbManager.fetchRecipe()

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
        ).map { resourceId -> getBytesFromResource(resourceId) }

        var imageIndex = 0
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_NAme))
                val text = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Description))
                val preparation = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Preparation))
                val items = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Recipe_Items))
                val time = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Cook_time))
                val kkal = cursor.getInt(cursor.getColumnIndex(DataBaseHalper.Calories))

                val recipeItem = RecipeItem(
                    name = name,
                    text = text,
                    time = time,
                    Kkal = kkal,
                    RecipeImage = images[imageIndex],
                    recipeItems = items,
                    Preparation = preparation
                )

                imageIndex = (imageIndex + 1) % images.size

                RecipeItemList.add(recipeItem)
                filteredList.add(recipeItem)

            } while (cursor.moveToNext())
        }
        adapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun setAdapter() {
        adapter = RecipeAdapter(requireActivity(), RecipeItemList, object : RecipeAdapter.OnItemClickListener{
            @SuppressLint("Range")
            override fun onItemClick(data: RecipeItem) {
                val intent = Intent(requireContext(), FullScreen::class.java)

                intent.putExtra("Kkal", data.Kkal)
                intent.putExtra("time", data.time)
                intent.putExtra("name", data.name)
                intent.putExtra("text", data.text)
                intent.putExtra("image", data.RecipeImage)
                intent.putExtra("items", data.recipeItems)
                intent.putExtra("preparation", data.Preparation)

                startActivityForResult(intent, 1)
            }
        })
        binding.RecipeList.adapter = adapter
        binding.RecipeList.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun getBytesFromResource(resourceId: Int): ByteArray {
        val inputStream = requireContext().resources.openRawResource(resourceId)
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(4096)
        var bytesRead: Int

        while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
            outputStream.write(buffer, 0, bytesRead)
        }
        return outputStream.toByteArray()
    }

    companion object {
        fun newInstance() = FragmentHome()
    }
}

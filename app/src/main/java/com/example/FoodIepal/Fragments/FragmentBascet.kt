package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FoodIepal.Entities.Basket
import com.example.FoodIepal.R
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.*
import com.example.FoodIepal.VIew.BascetAdapter
import com.example.FoodIepal.VIew.BascetViewModel
import com.example.FoodIepal.databinding.FragmentBascetBinding

class FragmentBascet : Fragment(), BascetAdapter.OnDeleteListener {
    private lateinit var adapter: BascetAdapter
    lateinit var binding: FragmentBascetBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: BascetViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBascetBinding.inflate(inflater)

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this)[BascetViewModel::class.java]

        adapter = BascetAdapter(this)

        binding.Bascet.adapter = adapter
        binding.Bascet.layoutManager = LinearLayoutManager(requireActivity())

        observeEvents()
        getToolbar()

        return binding.root
    }

    private fun getToolbar() {
        binding.toolbar3.apply {
            title = "Корзина"
            subtitle = sessionManager.getUserName()
            inflateMenu(R.menu.cutom_toolbar_basket)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEvents() {
        viewModel.allBascet.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.updateList(it as ArrayList<Basket>)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDelete(data: Basket) {
        Toast.makeText(requireContext(), "Удалено!", Toast.LENGTH_SHORT).show()
        viewModel.deleteBascet(data.name)
    }

    companion object {
        fun newInstance() = FragmentBascet()
    }
}
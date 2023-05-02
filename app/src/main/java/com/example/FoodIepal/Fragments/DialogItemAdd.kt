package com.example.FoodIepal.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogItemAddBinding

class DialogItemAdd : DialogFragment() {
    lateinit var binding: DialogItemAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogItemAddBinding.inflate(inflater)
        return binding.root
    }
}
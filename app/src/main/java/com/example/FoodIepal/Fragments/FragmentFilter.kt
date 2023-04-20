package com.example.FoodIepal.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.FoodIepal.R
import com.example.FoodIepal.databinding.FragmentFilterBinding

lateinit var binding: FragmentFilterBinding
private var selectedButton: Button? = null

class FragmentFilter : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater)

//        binding.button.setOnClickListener { selectButton(binding.button) }
//        binding.button.setOnClickListener { selectButton(binding.button2) }
//        binding.button.setOnClickListener { selectButton(binding.button4) }
        return binding.root
    }

    companion object {

        fun newInstance() = FragmentFilter()
    }

//    private fun selectButton(button: Button) {
//        if (selectedButton == null) {
//            // Ни одна кнопка не была выбрана
//            button.setBackgroundColor(Color.BLUE)
//            selectedButton = button
//        } else if (selectedButton == button) {
//            // Эта кнопка уже была выбрана, нужно ее сбросить
//            button.setBackgroundColor(Color.GRAY)
//            selectedButton = null
//        } else {
//            // Другая кнопка была выбрана, нужно ее сбросить и выбрать эту
//            selectedButton?.setBackgroundColor(Color.GRAY)
//            button.setBackgroundColor(Color.BLUE)
//            selectedButton = button
//        }
//    }
}
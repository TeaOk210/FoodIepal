package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSenddeskLayoutBinding

class DialogSetDesk: DialogFragment() {
    private lateinit var binding: DialogSenddeskLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSenddeskLayoutBinding.inflate(inflater)

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100, 1400)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textDesk.text.isNotEmpty()){
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
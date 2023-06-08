package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSendkkalLayoutBinding

class DialogSetDetails: DialogFragment() {
    private lateinit var binding: DialogSendkkalLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSendkkalLayoutBinding.inflate(inflater)

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100,1000)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            dismiss()
            DialogSetItems().show(parentFragmentManager, "")
        }

    }
}
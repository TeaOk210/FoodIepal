package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSendnameLayoutBinding

class DialogSetName: DialogFragment() {
    private lateinit var binding: DialogSendnameLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSendnameLayoutBinding.inflate(inflater)

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100,900)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textName.text.isNotEmpty() && binding.textPrep.text.isNotEmpty()) {
                dismiss()
                DialogSetDetails.newInstance(binding.textName.text.toString(), binding.textPrep.text.toString()).show(parentFragmentManager, "")
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(): DialogSetName = DialogSetName()
    }
}
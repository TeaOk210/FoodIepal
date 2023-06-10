package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        dialog?.window?.setLayout(1100,900)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textKkal.text.isNotEmpty() && binding.textTime.text.isNotEmpty()) {
                dismiss()
                DialogSetPhoto.newInstance(arguments?.getString("name").toString(),
                    arguments?.getString("prep").toString(),
                    binding.textKkal.text.toString().toInt(),
                    binding.textTime.text.toString().toInt()).show(parentFragmentManager, "")
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(name: String, prep: String): DialogSetDetails {
            val args = Bundle()
            args.putString("name", name)
            args.putString("prep", prep)
            val fragment = DialogSetDetails()
            fragment.arguments = args
            return fragment
        }
    }
}
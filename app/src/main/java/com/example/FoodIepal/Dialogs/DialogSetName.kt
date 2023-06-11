package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.databinding.DialogSendnameLayoutBinding

class DialogSetName: DialogFragment() {
    private lateinit var binding: DialogSendnameLayoutBinding
    private val dataModel: DataModel by activityViewModels()

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

                dataModel.name.value = binding.textName.text.toString()
                dataModel.prep.value = binding.textPrep.text.toString()

                DialogSetDetails.newInstance().show(parentFragmentManager, "")
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        fun newInstance(): DialogSetName = DialogSetName()
    }
}
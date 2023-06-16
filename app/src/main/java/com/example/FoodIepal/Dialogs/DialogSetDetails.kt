package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.databinding.DialogSendkkalLayoutBinding

class DialogSetDetails : DialogFragment() {
    private lateinit var binding: DialogSendkkalLayoutBinding
    private val dataModel: DataModel by activityViewModels()

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
        dialog?.window?.setLayout(1100, 900)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textKkal.text.isNotEmpty() && binding.textTime.text.isNotEmpty()) {
                dismiss()

                dataModel.apply {
                    Kkal.value = binding.textKkal.text.toString().toInt()
                    time.value = binding.textTime.text.toString().toInt()
                }

                DialogSetPhoto.newInstance().show(parentFragmentManager, "")
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(): DialogSetDetails = DialogSetDetails()
    }
}
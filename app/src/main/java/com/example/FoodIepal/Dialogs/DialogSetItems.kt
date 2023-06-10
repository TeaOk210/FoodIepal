package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSenditemsLayoutBinding

class DialogSetItems: DialogFragment() {
    private lateinit var binding: DialogSenditemsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSenditemsLayoutBinding.inflate(inflater)

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
            if (binding.textItem.text.isNotEmpty()) {
                dismiss()
                val name = arguments?.getString("name").toString()
                val prep = arguments?.getString("prep").toString()
                val Kkal = arguments?.getInt("Kkal")
                val time = arguments?.getInt("time")
                val photo = arguments?.getByteArray("image")
                DialogSetDesk.newInstance(name, prep, Kkal!!, time!!, photo!!, binding.textItem.text.toString()).show(parentFragmentManager, "")
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(name: String, prep: String, Kkal: Int, time: Int, image: ByteArray): DialogSetItems {
            val args = Bundle()
            args.putString("name", name)
            args.putString("prep", prep)
            args.putInt("Kkal", Kkal)
            args.putInt("time", time)
            args.putByteArray("image", image)
            val fragment = DialogSetItems()
            fragment.arguments = args
            return fragment
        }
    }
}
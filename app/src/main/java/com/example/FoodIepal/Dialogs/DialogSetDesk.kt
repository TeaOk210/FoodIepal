package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.DialogSenddeskLayoutBinding

class DialogSetDesk: DialogFragment() {
    private lateinit var binding: DialogSenddeskLayoutBinding
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSenddeskLayoutBinding.inflate(inflater)

        dbManager = DBManager(requireContext())

        sessionManager = SessionManager(requireContext())

        onClick()

        dbManager.open()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100, 1200)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textDesk.text.isNotEmpty()){
                dismiss()
                insertFood()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertFood() {
        val name = arguments?.getString("name").toString()
        val prep = arguments?.getString("prep").toString()
        val Kkal = arguments?.getInt("kkal")
        val time = arguments?.getInt("time")
        val image = arguments?.getByteArray("image")!!
        val items = arguments?.getString("items")!!
        val desk = binding.textDesk.text.toString()
        val login = sessionManager.getUserName()
        dbManager.insertPersonal(name, desk, items, Kkal!!, time!!, image, login, prep, true)
    }

    companion object {
        fun newInstance(name: String, prep: String, Kkal: Int, time: Int, image: ByteArray, items: String): DialogSetDesk {
            val args = Bundle()
            args.putString("name", name)
            args.putString("prep", prep)
            args.putInt("Kkal", Kkal)
            args.putInt("time", time)
            args.putByteArray("image", image)
            args.putString("items", items)
            val fragment = DialogSetDesk()
            fragment.arguments = args
            return fragment
        }
    }
}
package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.Utils.SessionManager
import com.example.FoodIepal.databinding.DialogSenddeskLayoutBinding

class DialogSetDesk: DialogFragment() {
    private lateinit var binding: DialogSenddeskLayoutBinding
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager
    private val dataModel: DataModel by activityViewModels()

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
        dialog?.window?.setLayout(1100, 1400)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            if (binding.textDesk.text.isNotEmpty()){
                dismiss()

                val name = dataModel.name.value.toString()
                val prep = dataModel.prep.value.toString()
                val Kkal = dataModel.Kkal.value!!
                val time = dataModel.time.value!!
                val image = dataModel.image.value!!
                val items = dataModel.setItems.value.toString()
                val desk = binding.textDesk.text.toString()
                val login = sessionManager.getUserName()
                val method = "insert"

                dbManager.insertFavorite(name, prep, items, Kkal, time, image, login, desk, method)
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance() = DialogSetDesk()
        }
}
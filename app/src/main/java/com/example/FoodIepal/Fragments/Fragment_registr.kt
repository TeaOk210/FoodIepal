package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataBaseHalper
import com.example.FoodIepal.databinding.FragmentRegistrBinding

class fragment_registr : Fragment() {
    lateinit var binding: FragmentRegistrBinding
    lateinit var dbManager: DBManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrBinding.inflate(inflater)
        dbManager = DBManager(requireActivity())
        dbManager.open()
        return binding.root
    }

    companion object {
        fun newInstance() = fragment_registr()
    }

    @SuppressLint("Range")
    fun checkRegistrationFields(): Boolean {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()
        val passwordConf = binding.passConf.text.toString()

        // Проверка на пустые поля
        if (login.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
            Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return false
        }

        // Проверка на совпадение паролей
        if (password != passwordConf) {
            Toast.makeText(activity, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            return false
        }

        // Проверка на наличие пользователя с таким логином в БД
        val cursor = dbManager.fetchReg()
        if (cursor.moveToFirst()) {
            do {
                val dbLogin = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Login))
                if (dbLogin == login) {
                    Toast.makeText(activity, "Пользователь с таким логином уже зарегистрирован", Toast.LENGTH_SHORT).show()
                    return false
                }
            } while (cursor.moveToNext())
        }

        return true
    }




    fun Registration() {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()
        dbManager.insertReg(login, password)
    }
}
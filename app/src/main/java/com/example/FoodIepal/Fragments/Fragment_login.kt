package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.FoodIepal.MainMenu
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataBaseHalper
import com.example.FoodIepal.databinding.FragmentLoginBinding

class fragment_login : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var dbManager: DBManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        dbManager = DBManager(requireActivity())
        dbManager.open()
        return binding.root
    }


    companion object {

        fun newInstance() = fragment_login()
    }

    @SuppressLint("Range")
    fun checkLoginAndPassword() {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()

        val cursor = dbManager.fetchReg()
        if (cursor.moveToFirst()) {
            do {
                val storedLogin = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Login))
                val storedPassword = cursor.getString(cursor.getColumnIndex(DataBaseHalper.Password))

                if (login == storedLogin && password == storedPassword) {
                    val mainIntent = Intent(activity, MainMenu::class.java)
                    startActivity(mainIntent)
                    activity?.finish()
                    return
                }
            } while (cursor.moveToNext())
        }
        Toast.makeText(activity, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
    }
}
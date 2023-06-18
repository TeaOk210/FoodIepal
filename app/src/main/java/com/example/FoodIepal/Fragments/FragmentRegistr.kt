package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DBManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.Utils.DataBaseHalper
import com.example.FoodIepal.databinding.FragmentRegistrBinding

class FragmentRegistr : Fragment() {
    lateinit var binding: FragmentRegistrBinding
    private lateinit var dbManager: DBManager
    private lateinit var sessionManager: SessionManager
    private lateinit var db: DataBase
//    private lateinit var dao:

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrBinding.inflate(inflater)

//        db = DataBase.getDatabase(requireContext())
//        dao = db.getUserDao()

        dbManager = DBManager(requireActivity())
        dbManager.open()

        sessionManager = SessionManager(requireContext())

        return binding.root
    }

    companion object {
        fun newInstance() = FragmentRegistr()
    }

    @SuppressLint("Range")
    fun checkRegistrationFields(): Boolean {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()
        val passwordConf = binding.passConf.text.toString()

        if (login.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
            Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != passwordConf) {
            Toast.makeText(activity, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            return false
        }

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




    fun registr() {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()

        sessionManager.setLogin(true)
        sessionManager.setUserName(login)

//        lifecycleScope.launch {
//            withContext(Dispatchers.IO) {
//                dao.insertUser(User(login, password))
//            }
//        }
    }
}





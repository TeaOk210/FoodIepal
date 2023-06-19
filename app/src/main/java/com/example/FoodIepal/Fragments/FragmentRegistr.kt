package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.FoodIepal.Entities.User
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.Utils.UserRepository
import com.example.FoodIepal.databinding.FragmentRegistrBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentRegistr : Fragment() {
    lateinit var binding: FragmentRegistrBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var db: DataBase
    private lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrBinding.inflate(inflater)

        db = DataBase.getDatabase(requireContext())
        repository = UserRepository(db.getUserDao())

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
        lifecycleScope.launch(Dispatchers.IO) {
            val result = repository.checkUserName(login)
            withContext(Dispatchers.Main) {
                if (result) {
                    Toast.makeText(
                        activity,
                        "Пользователь с таким логином уже зарегистрирован",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    registr(login, password)
                    activity?.finish()
                }
            }
        }
        return true
    }


    fun registr(login: String, password: String) {
        sessionManager.setLogin(true)
        sessionManager.setUserName(login)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertUser(User(null, login, password))
                DataBase.insertDefaultRecipes(requireContext())
            }
        }
    }
}





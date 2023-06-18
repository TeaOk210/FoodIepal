package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.FoodIepal.Activities.MainMenu
import com.example.FoodIepal.SessionManager
import com.example.FoodIepal.Utils.DataBase
import com.example.FoodIepal.Utils.UserRepository
import com.example.FoodIepal.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLogin : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var db: DataBase
    private lateinit var repository: UserRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        db = DataBase.getDatabase(requireContext())
        repository = UserRepository(db.getUserDao())

        sessionManager = SessionManager(requireContext())

        return binding.root
    }

    companion object {

        fun newInstance() = FragmentLogin()
    }

    @SuppressLint("Range")
     fun checkLoginAndPassword() {
        val login = binding.LogTxt.text.toString()
        val password = binding.passTXT.text.toString()

        lifecycleScope.launch(Dispatchers.IO) {
            val result = repository.checkLoginAndPassword(login, password)
            withContext(Dispatchers.Main) {
                if (result) {
                    val mainIntent = Intent(activity, MainMenu::class.java)
                    startActivity(mainIntent)
                    
                    sessionManager.setLogin(true)
                    sessionManager.setUserName(login)

                    activity?.finish()
                } else {
                    Toast.makeText(activity, "Неправильный логин или пароль", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
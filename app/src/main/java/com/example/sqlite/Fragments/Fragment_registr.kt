package com.example.sqlite.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sqlite.R
import com.example.sqlite.Utils.DBManager
import com.example.sqlite.databinding.FragmentRegistrBinding

class fragment_registr : Fragment() {
    lateinit var binding: FragmentRegistrBinding
    lateinit var dbManager: DBManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegistrBinding.inflate(inflater)
        dbManager = DBManager(requireActivity())
        dbManager.open()

        fun Registration(){
            val Login = binding.LogTxt.toString()
            val Pass = binding.passTXT.toString()
            val PassConf = binding.passConf.toString()
            if(PassConf == Pass) {
                dbManager.insert(Login, Pass)
            }
    }

        return binding.root
    }

    companion object {
        fun newInstance() = fragment_registr()
    }
}
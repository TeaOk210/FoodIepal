package com.example.FoodIepal.Utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Time

open class DataModel: ViewModel() {
    val Kkal: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val Time: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val Items: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
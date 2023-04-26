package com.example.FoodIepal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Time

open class DataModel: ViewModel() {
    val minKk: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val maxKk: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val minTt: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val maxTt: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}
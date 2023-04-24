package com.example.FoodIepal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val Kkal: MutableLiveData<IntRange> by lazy {
        MutableLiveData<IntRange>()
    }
}
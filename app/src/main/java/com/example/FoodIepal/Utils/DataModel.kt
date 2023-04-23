package com.example.FoodIepal.Utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val Kkal: MutableLiveData<IntRange> by lazy {
        MutableLiveData<IntRange>()
    }

    val Time: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()

    }

    val Items: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}

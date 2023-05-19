package com.example.FoodIepal.Utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
    val items: MutableLiveData<Array<String>> by lazy {
        MutableLiveData<Array<String>>()
    }
}
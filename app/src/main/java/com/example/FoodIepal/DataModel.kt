package com.example.FoodIepal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.Time

open class DataModel: ViewModel() {
    val Kkal: MutableLiveData<IntRange> by lazy {
        MutableLiveData<IntRange>()
    }
    val Time: MutableLiveData<IntRange> by lazy {
        MutableLiveData<IntRange>()
    }

}
package com.example.FoodIepal.Utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
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
    val items: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }
    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val prep: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val Kkal: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val time: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val image: MutableLiveData<ByteArray> by lazy {
        MutableLiveData<ByteArray>()
    }
    val setItems: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val desk: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
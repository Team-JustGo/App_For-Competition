package com.justgo.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.justgo.Util.DisposableViewModel


class MainViewModel : DisposableViewModel() {
    private val _selectedItem = MutableLiveData<Int>().apply { value = 1 }
    val selectedItem: LiveData<Int> get() = _selectedItem

    fun changeSelectedItem(value: Int) {
        _selectedItem.value = value
        Log.d("MainViewModel", "selectedItem value is changed: ${selectedItem.value}")
    }
}
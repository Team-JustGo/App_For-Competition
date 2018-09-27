package com.justgo.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.justgo.Util.DisposableViewModel
import com.justgo.Util.SingleLiveEvent


class MainViewModel : DisposableViewModel() {
    private val _selectedFragment = MutableLiveData<Int>().apply { value = 1 }
    private val _selectedSubject = MutableLiveData<ArrayList<String>>().apply { arrayOf("") }
    private val _selectableSubject = MutableLiveData<ArrayList<String>>()
            .apply { value = arrayListOf("Art Gallery", "Religious architecture", "Department Store", "Shopping mall", "Electronics Store", "Home goods Store", "Museum", "Park") }
    private val _minRange = MutableLiveData<Int>().apply { value = 0 }
    private val _maxRange = MutableLiveData<Int>().apply { value = 50 }
    private val _getTravelListEvent = SingleLiveEvent<Any>()

    val selectedFragment: LiveData<Int> get() = _selectedFragment
    val selectedSubject: LiveData<ArrayList<String>> get() = _selectedSubject
    val selectableSubject: LiveData<ArrayList<String>> get() = _selectableSubject
    val minRange: LiveData<Int> get() = _minRange
    val maxRange: LiveData<Int> get() = _maxRange
    val getTravelListEvent: LiveData<Any> get() = _getTravelListEvent

    fun changeSelectedItem(value: Int) {
        _selectedFragment.value = value
        Log.d("MainViewModel", "selectedFragment value is changed: ${selectedFragment.value}")
    }

    fun setMinRange(value: Int) {
        _minRange.value = value
    }

    fun setMaxRange(value: Int) {
        _maxRange.value = value
    }

    fun getSelectedItem(): Int = selectedFragment.value!!
}
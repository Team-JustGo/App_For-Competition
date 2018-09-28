package com.justgo.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.justgo.Connecter.getTourList
import com.justgo.Util.DisposableViewModel
import com.justgo.Util.SingleLiveEvent
import org.jetbrains.anko.toast


class MainViewModel : DisposableViewModel() {
    private val _selectedFragment = MutableLiveData<Int>().apply { value = 1 }
    private val _selectedSubject = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val selectedSubjectList = arrayListOf<String>()
    private val _selectableSubject = MutableLiveData<ArrayList<String>>()
            .apply { value = arrayListOf("Art Gallery", "Religious architecture", "Department Store", "Shopping mall", "Electronics Store", "Home goods Store", "Museum", "Park") }
    val selectableSubjectList = arrayListOf("Art Gallery", "Religious architecture", "Department Store", "Shopping mall", "Electronics Store", "Home goods Store", "Museum", "Park")
    private val _minRange = MutableLiveData<Int>().apply { value = 0 }
    private val _maxRange = MutableLiveData<Int>().apply { value = 50 }
    private val _getTravelListEvent = SingleLiveEvent<Any>()
    val selectableSubjectDeletedEvent = SingleLiveEvent<Int>()
    val selectedSubjectDeletedEvent = SingleLiveEvent<Int>()
    private val _lat = MutableLiveData<Double>().apply { value = 0.0 }
    private val _lng = MutableLiveData<Double>().apply { value = 0.0 }


    val selectedFragment: LiveData<Int> get() = _selectedFragment
    val selectedSubject: LiveData<ArrayList<String>> get() = _selectedSubject
    val selectableSubject: LiveData<ArrayList<String>> get() = _selectableSubject
    val minRange: LiveData<Int> get() = _minRange
    val maxRange: LiveData<Int> get() = _maxRange
    val getTravelListEvent: LiveData<Any> get() = _getTravelListEvent
    val lat: LiveData<Double> get() = _lat
    val lng: LiveData<Double> get() = _lng

    fun changeSelectedItem(value: Int) {
        _selectedFragment.value = value
        Log.d("MainViewModel", "selectedFragment value is changed: ${selectedFragment.value}")
    }

    fun addSelectedSubject(value: String) {
        selectableSubjectList.remove(value)
        _selectableSubject.value = selectableSubjectList
        selectedSubjectList.add(value)
        _selectedSubject.value = selectedSubjectList
    }

    fun addSelectableSubject(value: String) {
        selectedSubjectList.remove(value)
        _selectedSubject.value = selectedSubjectList
        selectableSubjectList.add(value)
        _selectableSubject.value = selectableSubjectList
    }

    fun setMinRange(value: Int) {
        _minRange.value = value
    }

    fun setMaxRange(value: Int) {
        _maxRange.value = value
    }

    fun setLat(value: Double) {
        _lat.value = value
    }

    fun setLng(value: Double) {
        _lng.value = value
    }

    fun getTourList() {

        val theme = selectableSubject.value?.reduce { x, y -> "$x ,$y" }
        val min = minRange.value?.toInt()?.times(1000)
        val max = maxRange.value?.toInt()?.times(1000)
        val lat = lat.value
        val lng = lng.value
        if (lat != null && lng != null && theme != null && min != null && max != null) {
            getTourList(lat, lng, theme, min, max) {
                onSuccess = {
                    //                toast("${code()}")
                }
                onFailure = {
                    //                toast("왜안되는데;")
                }
            }
        }
    }

    fun generateSubject(): String {
        var sibal = ""
//        selectedSubject.value!!.forEach {
//            sibal += when (it) {
//                "Art Gallery" -> "art_gallery, "
//                ""
//                else -> ""
//            }
//        }
        return "시발련"
    }

    fun getSelectedItem(): Int = selectedFragment.value!!
}
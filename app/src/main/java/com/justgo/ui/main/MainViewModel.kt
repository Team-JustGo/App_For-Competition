package com.justgo.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.justgo.Connecter.getTourList
import com.justgo.Util.DisposableViewModel
import com.justgo.Util.SingleLiveEvent
import org.jetbrains.anko.toast


class MainViewModel : DisposableViewModel() {
    private val _selectedFragment = MutableLiveData<Int>().apply { value = 0 }
    private val _selectedSubject = MutableLiveData<ArrayList<String>>().apply { value = arrayListOf() }
    val selectedSubjectList = arrayListOf<String>()
    private val _selectableSubject = MutableLiveData<ArrayList<String>>()
            .apply { value = arrayListOf("놀이공원 및 오락시설", "서점", "아트 갤러리", "백화점", "박물관", "공원", "동물원", "옷가게", "영화관", "쇼핑몰") }
    val selectableSubjectList = arrayListOf("놀이공원 및 오락시설", "서점", "아트 갤러리", "백화점", "박물관", "공원", "동물원", "옷가게", "영화관", "쇼핑몰")
    private val _minRange = MutableLiveData<Int>().apply { value = 0 }
    private val _maxRange = MutableLiveData<Int>().apply { value = 50 }
    private val _getTravelListEvent = SingleLiveEvent<Any>()
    val selectableSubjectDeletedEvent = SingleLiveEvent<Int>()
    val selectedSubjectDeletedEvent = SingleLiveEvent<Int>()
    private val _lat = MutableLiveData<Double>().apply { value = 0.0 }
    private val _lng = MutableLiveData<Double>().apply { value = 0.0 }
    private val _trans = MutableLiveData<Int>().apply { value = 0 }


    val selectedFragment: LiveData<Int> get() = _selectedFragment
    val selectedSubject: LiveData<ArrayList<String>> get() = _selectedSubject
    val selectableSubject: LiveData<ArrayList<String>> get() = _selectableSubject
    val minRange: LiveData<Int> get() = _minRange
    val maxRange: LiveData<Int> get() = _maxRange
    val getTravelListEvent: LiveData<Any> get() = _getTravelListEvent
    val lat: LiveData<Double> get() = _lat
    val lng: LiveData<Double> get() = _lng
    val trans: LiveData<Int> get() = _trans

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

    fun changeTrans(value: Int) {
        _trans.value = value
    }

//    val getStatusName = when (selectedFragment.value) {
//        1 -> "시작장소 선택"
//        2 -> "교통수단 선택"
//        3 -> "여행 주제 선택"
//        4 -> "거리 제한 선택"
//        else -> "sibal"
//    }
//
//    fun getStatusStep() = when (selectedFragment.value) {
//        1 -> "1 / 4"
//        2 -> "2 / 4"
//        3 -> "3 / 4"
//        4 -> "4 / 4"
//        else -> "()"
//    }

    fun getTourList() {

        val theme = generateSubject()
        val min = minRange.value?.toInt()?.times(1000)
        val max = maxRange.value?.toInt()?.times(1000)
        val lat = lat.value
        val lng = lng.value
        if (lat != null && lng != null && min != null && max != null) {
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
        val sibal = StringBuilder()
        selectedSubjectList.forEach {
            sibal.append(when (it) {
                "놀이공원 및 오락시설" -> "amusement_park,"
                "서점" -> "book_store,"
                "아트 갤러리" -> "art_gallery,"
                "백화점" -> "department_store,"
                "박물관" -> "museum,"
                "공원" -> "park,"
                "동물원" -> "zoo,"
                "옷가게" -> "clothing_store,"
                "영화관" -> "movie_theater,"
                "쇼핑몰" -> "shopping_mall,"
                else -> ""
            })
        }

        sibal.deleteCharAt(sibal.length - 1)
//        sibal.removeRange(sibal.length - 1, sibal.length - 1)
        return sibal.toString()
    }

    fun getSelectedItem(): Int = selectedFragment.value!!
}
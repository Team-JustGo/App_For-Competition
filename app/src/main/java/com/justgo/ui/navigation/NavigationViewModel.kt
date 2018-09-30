package com.justgo.ui.navigation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.justgo.Connecter.getDirection
import com.justgo.Model.DirectionModel

class NavigationViewModel : ViewModel() {
    val _locationModel = MutableLiveData<ArrayList<DirectionModel.Point>>().apply { value = arrayListOf() }
    var direction = ArrayList<DirectionModel.Point>()
    val _locationIndex = MutableLiveData<Int>()

    val locationModel: LiveData<ArrayList<DirectionModel.Point>> get() = _locationModel
    val locationIndex: LiveData<Int> get() = _locationIndex
    fun getNavigation(transport: String, lat: Double, lng: Double, desLat: Double, desLng: Double) {
        getDirection(transport, lat, lng, desLat, desLng) {
            onSuccess = {
                direction = body()!!.points
                _locationIndex.postValue(0)
            }
        }
    }

    fun compareLocation(lat: Double, lng: Double) {
        direction[locationIndex.value!!]
    }
}
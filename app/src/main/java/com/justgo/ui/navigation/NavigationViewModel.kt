package com.justgo.ui.navigation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.justgo.Connecter.getDirection
import com.justgo.Model.DirectionModel
import com.justgo.Util.SingleLiveEvent
import io.reactivex.Single

class NavigationViewModel : ViewModel() {
    val _locationModel = MutableLiveData<ArrayList<DirectionModel.Point>>().apply { value = arrayListOf(DirectionModel.Point("", 0.0, 0.0)) }
    var direction = ArrayList<DirectionModel.Point>()
    val _locationIndex = MutableLiveData<Int>().apply { value = 0 }
    var index = 0
    val changeTextLiveEvent = SingleLiveEvent<Any>()
    val travelFinishEvent = SingleLiveEvent<Any>()
    var polyLineEvent = SingleLiveEvent<String>()
    var polyLine = ""
    val locationModel: LiveData<ArrayList<DirectionModel.Point>> get() = _locationModel
    val locationIndex: LiveData<Int> get() = _locationIndex
    fun getNavigation(transport: String, lat: Double, lng: Double, desLat: Double, desLng: Double) {
        getDirection(transport, lat, lng, desLat, desLng) {
            onSuccess = {
                direction = body()!!.points
                polyLine = body()!!.polyline
                polyLineEvent.call()
                changeTextLiveEvent.call()
            }
        }
    }

    fun compareLocation(lat: Double, lng: Double) {
        val itr = direction.iterator()
        if (index < direction.size - 1) {
            travelFinishEvent.call()
            Log.d("되냐?", "되네")
            val direction = direction[index + 1]
            direction.lat = String.format("%.5f", direction.lat).toDouble()
            direction.lng = String.format("%.5f", direction.lng).toDouble()

            Log.d("NavigationViewModel", "in direction: ${direction.lat}  ${direction.lng}")
            Log.d("NavigationViewModel", "${direction.lat - 0.001}, ${direction.lng - 0.001} < ${lat.toFive()}  ${lng.toFive()} < ${direction.lat + 0.001}  ${direction.lng + 0.001}")
            if (direction.lat - 0.001 < lat.toFive() && lat.toFive() < direction.lat + 0.001) {
                if (direction.lng - 0.001 < lng.toFive() && lng.toFive() < direction.lng + 0.001) {
                    _locationIndex.postValue(_locationIndex.value!! + 1)
                    Log.d("NavigationViewModel", "Success")
                    changeTextLiveEvent.call()
                    index++
                    itr.next()
                }
            }
        } else if (direction.size != 0) {
            travelFinishEvent.call()
        }
    }

    fun Double.toFive() = String.format("%.5f", this).toDouble()
}
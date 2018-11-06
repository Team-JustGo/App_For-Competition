package com.justgo.ui.navigation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.justgo.connecter.API
import com.justgo.connecter.getDirection
import com.justgo.model.DirectionModel
import com.justgo.util.SingleLiveEvent

class NavigationViewModel() : ViewModel() {
    val _direction = MutableLiveData<ArrayList<DirectionModel.Point>>()
    val _transit = MutableLiveData<String>()
    val _type = MutableLiveData<String>()
    val _polyLine = MutableLiveData<String>()

    val direction get() = _direction as LiveData<ArrayList<DirectionModel.Point>>
    val travelFinishEvent = SingleLiveEvent<Any>()
    val transit get() = _transit as LiveData<String>
    val type get() = _type as LiveData<String>
    val polyLine get() = _polyLine as LiveData<String>
    var index = 0




    fun getNavigation(transport: String, lat: Double, lng: Double, desLat: Double, desLng: Double) {
        getDirection(transport, lat, lng, desLat, desLng) {
            onSuccess = {
                _direction.value = body()!!.points
                _polyLine.value = body()!!.polyline.replace("\\", """\""")
                _transit.value = _direction.value!![index].instruction
                _type.value = _direction.value!![index + 1].let { " With ${it.mode}" }
                /*changeTextLiveEvent.call()*/
            }
        }

    }

    fun compareLocation(lat: Double, lng: Double) {
        direction.value?.let { directionLiveData ->
            if (index < directionLiveData.size - 1) {
                Log.d("되냐?", "되네")
                val direction = directionLiveData[index + 1]
                direction.lat = String.format("%.5f", direction.lat).toDouble()
                direction.lng = String.format("%.5f", direction.lng).toDouble()

                Log.d("NavigationViewModel", "in _direction: ${direction.lat}  ${direction.lng}")
                Log.d("NavigationViewModel", "${direction.lat - 0.001}, ${direction.lng - 0.001} < ${lat.toFive()}  ${lng.toFive()} < ${direction.lat + 0.001}  ${direction.lng + 0.001}")
                if (direction.lat - 0.001 < lat.toFive() && lat.toFive() < direction.lat + 0.001) {
                    if (direction.lng - 0.001 < lng.toFive() && lng.toFive() < direction.lng + 0.001) {
                        Log.d("NavigationViewModel", "Success")
                        index++
                        _transit.value = directionLiveData[index].instruction
                        _type.value = directionLiveData[index + 1].let { " With ${it.mode}" }
                    }
                }
            } else if (directionLiveData.size != 0) {
                travelFinishEvent.call()
            }
        }
    }

    fun Double.toFive() = String.format("%.5f", this).toDouble()
}
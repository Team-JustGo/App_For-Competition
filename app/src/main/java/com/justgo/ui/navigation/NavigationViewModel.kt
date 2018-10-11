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
    var direction = ArrayList<DirectionModel.Point>()
    var index = 0
    val changeTextLiveEvent = SingleLiveEvent<Any>()
    val travelFinishEvent = SingleLiveEvent<Any>()
    val transit = MutableLiveData<String>()/*.apply { value = "" }*/
    val type = MutableLiveData<String>()/*.apply { value = "" }*/
    val polyLineEvent = SingleLiveEvent<String>()
    var polyLine = ""

    fun getNavigation(transport: String, lat: Double, lng: Double, desLat: Double, desLng: Double) {
        getDirection(transport, lat, lng, desLat, desLng) {
            onSuccess = {
                direction = body()!!.points
                polyLine = body()!!.polyline.replace("\\", """\""")
                polyLineEvent.call()
                transit.value = direction[index].instruction
                type.value = direction[index + 1].let { " With ${it.mode}" }
                /*changeTextLiveEvent.call()*/
            }
        }

    }

    fun compareLocation(lat: Double, lng: Double) {
        if (index < direction.size - 1) {
            Log.d("되냐?", "되네")
            val direction = direction[index + 1]
            direction.lat = String.format("%.5f", direction.lat).toDouble()
            direction.lng = String.format("%.5f", direction.lng).toDouble()

            Log.d("NavigationViewModel", "in direction: ${direction.lat}  ${direction.lng}")
            Log.d("NavigationViewModel", "${direction.lat - 0.001}, ${direction.lng - 0.001} < ${lat.toFive()}  ${lng.toFive()} < ${direction.lat + 0.001}  ${direction.lng + 0.001}")
            if (direction.lat - 0.001 < lat.toFive() && lat.toFive() < direction.lat + 0.001) {
                if (direction.lng - 0.001 < lng.toFive() && lng.toFive() < direction.lng + 0.001) {
                    Log.d("NavigationViewModel", "Success")
                    index++
                    transit.value = this.direction[index].instruction
                    type.value = this.direction[index + 1].let { " With ${it.mode}" }
                }
            }
        } else if (direction.size != 0) {
            travelFinishEvent.call()
        }
    }

    fun Double.toFive() = String.format("%.5f", this).toDouble()
}
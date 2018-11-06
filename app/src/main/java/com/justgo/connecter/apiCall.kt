package com.justgo.connecter

import com.google.gson.JsonObject
import com.justgo.model.DirectionModel
import com.justgo.model.LoginResponseModel
import com.justgo.model.TourInfoModel
import com.justgo.model.TourListModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val api = Connecter.createApi()

fun login(userId: String, name: String, model: ConnectModel<LoginResponseModel>.() -> Unit) = connectWIthModel(api.auth_user(userId, name), model)

//fun login(userId: String, name: String, picture: MultipartBody.Part, model: ConnectModel<Void>.() -> Unit) = connectWIthModel(api.login(userId, name, picture), model)
fun main(token: String, model: ConnectModel<Void>.() -> Unit) = connectWIthModel(api.main(token), model)

fun changeImage(token: String, image: MultipartBody.Part, model: ConnectModel<Void>.() -> Unit) = connectWIthModel(api.changeImage(token, image), model)
fun changeName(token: String, profileName: String, model: ConnectModel<Void>.() -> Unit) = connectWIthModel(api.changeName(token, profileName), model)
fun saveTourSpot(token: String, tourId: String, model: ConnectModel<Void>.() -> Unit) = connectWIthModel(api.saveTourSpot(token, tourId), model)
fun getTourList(lat: Double, lng: Double, theme: String, min: Int, max: Int, model: ConnectModel<TourListModel>.() -> Unit) = connectWIthModel(api.getTourList(lat, lng, theme, min, max), model)
fun getTourInfo(id: String, model: ConnectModel<TourInfoModel>.() -> Unit) = connectWIthModel(api.getTourInfo(id), model)
fun getDirection(transport: String, lat: Double, lng: Double, desLat: Double, desLng: Double, model: ConnectModel<DirectionModel>.() -> Unit) = connectWIthModel(api.getDirection(transport, lat, lng, desLat, desLng), model)
fun getProfile(header: String, model: ConnectModel<JsonObject>.() -> Unit) = connectWIthModel(api.getProfile(header), model)

data class ConnectModel<T>(var onSuccess: (Response<T>.() -> Unit)? = null, var onFailure: (() -> Unit)? = null)

fun <T> connectWIthModel(connect: Call<T>, model: ConnectModel<T>.() -> Unit) {
    val connectModel = ConnectModel<T>()
    connectModel.model()
    connect.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            connectModel.onSuccess?.let { response?.it() }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            connectModel.onFailure?.let { it() }
        }

    })
}

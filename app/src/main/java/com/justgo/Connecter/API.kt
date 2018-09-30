package com.justgo.Connecter

import com.google.gson.JsonObject
import com.justgo.Model.DirectionModel
import com.justgo.Model.LoginResponseModel
import retrofit2.Call
import com.justgo.Model.TourInfoModel
import com.justgo.Model.TourListModel
import okhttp3.MultipartBody
import retrofit2.http.*

interface API {

    @POST("user/login")
    @FormUrlEncoded
    fun post_user(@Field("userId") userId: String): Call<LoginResponseModel>

    @POST("user/login")
    @FormUrlEncoded
    fun auth_user(@Field("userId") userId: String, @Field("name") name: String): Call<LoginResponseModel>

    @POST("user/main")
    fun main(@Header("X-Access-Token") token: String): Call<Void>

    @PUT("user/profile/image")
    @FormUrlEncoded
    @Multipart
    fun changeImage(@Header("X-Access-Token") token: String, @Field("profile-image") profileImage: MultipartBody.Part): Call<Void>

    @PUT("user/profile/name")
    @FormUrlEncoded
    fun changeName(@Header("X-Access-Token") token: String, @Field("profile-image") profileName: String): Call<Void>

    @POST("user/tour-spot")
    @FormUrlEncoded
    fun saveTourSpot(@Header("X-Access-Token") token: String, @Field("tourId") tourId: String): Call<Void>

    @GET("travel/tour-list")
    fun getTourList(@Query("lat") lat: Double, @Query("lng") lng: Double, @Query("theme") theme: String, @Query("minDistance") min: Int, @Query("maxDistance") max: Int): Call<TourListModel>

    @GET("travel/{id}/tour-info")
    fun getTourInfo(@Path("id") id: String): Call<TourInfoModel>

    @GET("travel/direction")
    fun getDirection(@Query("transport") transport: String, @Query("depLat") lat: Double, @Query("depLng") lng: Double, @Query("desLat") desLat: Double, @Query("desLng") desLng: Double): Call<DirectionModel>

    @GET("user/profile")
    fun getProfile(@Header("X-Access-Token") header: String): Call<JsonObject>
}
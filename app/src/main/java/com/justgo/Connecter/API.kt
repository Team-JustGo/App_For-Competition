package com.justgo.Connecter

import com.justgo.Model.TourInfoModel
import com.justgo.Model.TourListModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("user/login")
    @FormUrlEncoded
    @Multipart
    fun login(@Field("userId") userId: String, @Field("name") name: String, @Part picture: MultipartBody.Part): Call<Void>

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
    fun getTourList(@QueryMap query: Map<String, Any>): Call<TourListModel>

    @GET("travel/{id}/tour-info")
    fun getTourInfo(@Path("id") id: String): Call<TourInfoModel>
}
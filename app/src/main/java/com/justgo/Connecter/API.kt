package com.justgo.Connecter

import android.media.Image
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST

interface API{

    @POST("/user/login")
    @FormUrlEncoded
    fun post_user(@Field("userId") userId: String) : Call<RetrofitRepo>


    @POST("/user/login")
    @Multipart
    @FormUrlEncoded
    fun auth_user(@Field("user") userId: String, @Field("name") name : String, @Field("image") image: Image) : Call<RetrofitRepo>
//    fun post_name(@Field("name") name:String) : Call<RetrofitRepo>
//    fun post_image(@Field("image") picture: String) : Call<RetrofitRepo>
}
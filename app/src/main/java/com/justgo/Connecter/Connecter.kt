package com.justgo.Connecter

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Connecter {
    lateinit var retrofit: Retrofit
    lateinit var api: API

    init {
        retrofit = Retrofit
                .Builder()
                .baseUrl("http://ec2-52-79-240-33.ap-northeast-2.compute.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(API::class.java)
    }
}
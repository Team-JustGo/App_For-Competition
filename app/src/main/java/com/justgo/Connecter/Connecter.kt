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
                .baseUrl("Url will here!")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(API::class.java)
    }
}
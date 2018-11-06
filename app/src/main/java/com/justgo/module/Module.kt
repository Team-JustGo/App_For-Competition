package com.justgo.module

import com.justgo.connecter.Connecter
import com.justgo.ui.navigation.NavigationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
                .baseUrl("http://ec2-52-79-240-33.ap-northeast-2.compute.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
    }

    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder().addInterceptor(get()).build()
    }

//    val interceptor = HttpLoggingInterceptor()
//    interceptor.level = HttpLoggingInterceptor.Level.BODY
//    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
}

val navigationModule = module {
    viewModel { NavigationViewModel() }
}
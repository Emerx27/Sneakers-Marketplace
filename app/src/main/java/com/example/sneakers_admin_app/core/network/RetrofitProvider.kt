package com.example.sneakers_admin_app.core.network

import android.content.Context
import com.example.sneakers_admin_app.core.storage.UserPreferences
import com.example.sneakers_admin_app.features.auth.network.AuthApiService
import com.example.sneakers_admin_app.features.sneakers.network.SneakersApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitProvider {
    private const val BASE_URL = "http://192.168.1.152:3000/api/"

    private lateinit var appContext: Context
    fun init(context: Context) {
        appContext = context.applicationContext
    }
    private val userPreferences by lazy {
        UserPreferences(appContext)
    }
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(userPreferences)
            )
            .build()
    }
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val sneakersApi: SneakersApiService by lazy {
        retrofit.create(SneakersApiService::class.java)
    }
}
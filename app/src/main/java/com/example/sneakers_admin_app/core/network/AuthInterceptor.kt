package com.example.sneakers_admin_app.core.network

import com.example.sneakers_admin_app.core.storage.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userPreferences: UserPreferences) : Interceptor {
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val token = runBlocking {
            userPreferences.getToken()
        }

        val request =
            chain.request()
            .newBuilder()
            .addHeader(
            "Authorization",
            "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}
package com.example.sneakers_admin_app.features.auth.network

import com.example.sneakers_admin_app.features.auth.models.login.LoginRequest
import com.example.sneakers_admin_app.features.auth.models.login.LoginResponse
import com.example.sneakers_admin_app.features.auth.models.register.RegisterRequest
import com.example.sneakers_admin_app.features.auth.models.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ) : Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ) : Response<LoginResponse>
}
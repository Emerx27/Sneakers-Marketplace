package com.example.sneakers_admin_app.features.profile.network

import com.example.sneakers_admin_app.features.sneakers.models.SneakerPreview
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApiService {
    @GET("sneakers/my")
    suspend fun getMySneakers(): Response<List<SneakerPreview>>
}
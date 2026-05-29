package com.example.sneakers_admin_app.features.sneakers.network

import com.example.sneakers_admin_app.features.sneakers.models.SneakerPreview
import retrofit2.Response
import retrofit2.http.GET

interface SneakersApiService {
    @GET("sneakers")
    suspend fun getAllSneakers(): Response<List<SneakerPreview>>
}
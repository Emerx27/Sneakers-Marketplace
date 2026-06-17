package com.example.sneakers_admin_app.features.sneakers.network

import com.example.sneakers_admin_app.features.sneakers.models.SneakerDetailResponse
import com.example.sneakers_admin_app.features.sneakers.models.publish.PublishSneakerRequest
import com.example.sneakers_admin_app.features.sneakers.models.SneakerPreview
import com.example.sneakers_admin_app.features.sneakers.models.publish.PublishSneakerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SneakersApiService {
    @GET("sneakers")
    suspend fun getAllSneakers(): Response<List<SneakerPreview>>

    @POST("sneakers")
    suspend fun publishSneaker(
        @Body request: PublishSneakerRequest
    ) : Response<PublishSneakerResponse>

    @GET("sneakers/{id}")
    suspend fun getSneakerById(
        @Path("id") id: Long?
    ): Response<SneakerDetailResponse>
}
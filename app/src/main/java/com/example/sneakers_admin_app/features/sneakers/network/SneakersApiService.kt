package com.example.sneakers_admin_app.features.sneakers.network

import com.example.sneakers_admin_app.features.sneakers.models.SneakerDetailResponse
import com.example.sneakers_admin_app.features.sneakers.models.publish.PublishSneakerRequest
import com.example.sneakers_admin_app.features.sneakers.models.SneakerPreview
import com.example.sneakers_admin_app.features.sneakers.models.publish.PublishSneakerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface SneakersApiService {
    @GET("sneakers")
    suspend fun getAllSneakers(): Response<List<SneakerPreview>>

    @Multipart
    @POST("sneakers")
    suspend fun publishSneaker(
        @Part("brand")
        brand: RequestBody,
        @Part("model")
        model: RequestBody,
        @Part("sku")
        sku: RequestBody,
        @Part("price")
        price: RequestBody,
        @Part("condition")
        condition: RequestBody,
        @Part("description")
        description: RequestBody?,
        @Part
        images: List<MultipartBody.Part>
    ): Response<PublishSneakerResponse>

    @GET("sneakers/{id}")
    suspend fun getSneakerById(
        @Path("id") id: Long?
    ): Response<SneakerDetailResponse>
}
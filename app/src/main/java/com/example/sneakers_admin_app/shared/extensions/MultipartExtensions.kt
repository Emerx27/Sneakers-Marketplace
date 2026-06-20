package com.example.sneakers_admin_app.shared.extensions

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toPart(): RequestBody {
    return toRequestBody(
        "text/plain".toMediaType()
    )
}

fun Uri.toMultipartPart(
    context: Context
): MultipartBody.Part {

    val mimeType =
        context.contentResolver.getType(this)
            ?: "image/jpeg"

    val bytes =
        context.contentResolver
            .openInputStream(this)
            ?.readBytes()
            ?: throw Exception("Cannot read image")

    val requestBody =
        bytes.toRequestBody(
            mimeType.toMediaType()
        )

    return MultipartBody.Part.createFormData(
        "images",
        "image.jpg",
        requestBody
    )
}
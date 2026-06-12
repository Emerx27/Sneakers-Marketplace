package com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers_admin_app.core.models.errors.MessageErrorResponse
import com.example.sneakers_admin_app.core.models.errors.ValidationErrorResponse
import com.example.sneakers_admin_app.core.network.RetrofitProvider
import com.example.sneakers_admin_app.features.sneakers.models.publish.PublishSneakerRequest
import com.google.gson.Gson
import kotlinx.coroutines.launch

class PublishSneakerViewModel : ViewModel() {
    var brand by mutableStateOf("")
        private set

    var model by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var condition by mutableStateOf("")
        private set

    var sku by mutableStateOf("")
        private set

    var brandError by mutableStateOf<String?>(null)
        private set

    var modelError by mutableStateOf<String?>(null)
        private set

    var descriptionError by mutableStateOf<String?>(null)
        private set

    var priceError by mutableStateOf<String?>(null)
        private set
    var conditionError by mutableStateOf<String?>(null)
        private set

    var skuError by mutableStateOf<String?>(null)
        private set

    var generalError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isSuccess by mutableStateOf(false)
        private set

    fun onBrandChange(value: String) {
        brand = value
        brandError = null
    }

    fun onModelChange(value: String) {
        model = value
        modelError = null
    }

    fun onDescriptionChange(value: String) {
        description = value
        descriptionError = null
    }

    fun onPriceChange(value: String) {
        price = value
        priceError = null
    }

    fun onConditionChange(value: String) {
        condition = value
        conditionError = null
    }

    fun onSkuChange(value: String) {
        sku = value
        skuError = null
    }

    fun clearErrors() {
        brandError = null
        modelError = null
        descriptionError = null
        priceError = null
        conditionError = null
        skuError = null
        generalError = null
    }

    private fun validateForm(): Boolean {
        var hasErrors = false

        if (brand.isBlank()) {
            brandError = "Sneaker brand is required"
            hasErrors = true
        }

        if (model.isBlank()) {
            modelError = "Sneaker model is required"
            hasErrors = true
        }

        if (sku.isBlank()) {
            skuError = "SKU is required"
            hasErrors = true
        }

        if (condition.isBlank()) {
            conditionError = "Condition is required"
            hasErrors = true
        }

        if (price.isBlank()) {
            priceError = "Price is required"
            hasErrors = true
        }

        if (hasErrors) {
            return false
        }

        val parsedPrice = price.toDoubleOrNull()

        if (parsedPrice == null) {
            priceError = "Price must be a valid number"
            return false
        }

        if (parsedPrice <= 0) {
            priceError = "Price must be a positive number"
            return false
        }

        return true
    }

    fun clearErrorMessage() {
        generalError = null
    }

    fun publishSneaker() {
        if (isLoading) return

        isLoading = true

        clearErrors()

        if (!validateForm()) {
            isLoading = false
            return
        }

        isSuccess = false

        val parsedPrice = price.toDouble()

        viewModelScope.launch {
            try {
                val response =
                    RetrofitProvider
                        .sneakersApi
                        .publishSneaker(
                            PublishSneakerRequest(
                                brand = brand,
                                model = model,
                                sku = sku,
                                price = parsedPrice,
                                condition = condition,
                                description = description.ifBlank { null }
                            )
                        )

                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        isSuccess = true
                    }
                } else {
                    isLoading = false

                    val errorJson =
                        response.errorBody()?.string()

                    try {

                        val validationErrors =
                            Gson().fromJson(
                                errorJson,
                                ValidationErrorResponse::class.java
                            )
                        brandError =
                            validationErrors.error["brand"]

                        modelError =
                            validationErrors.error["model"]

                        skuError =
                            validationErrors.error["sku"]

                        descriptionError =
                            validationErrors.error["description"]

                        priceError =
                            validationErrors.error["price"]

                        conditionError =
                            validationErrors.error["condition"]

                    } catch (_: Exception) {
                        val messageError =
                            Gson().fromJson(
                                errorJson,
                                MessageErrorResponse::class.java
                            )

                        generalError =
                            messageError.error
                    }
                }

            } catch (_: Exception) {
                isLoading = false

                generalError =
                    "No internet connection"
            }
        }
    }
}
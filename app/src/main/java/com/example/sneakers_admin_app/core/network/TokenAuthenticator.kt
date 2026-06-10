package com.example.sneakers_admin_app.core.network

import com.example.sneakers_admin_app.core.models.tokens.RefreshRequest
import com.example.sneakers_admin_app.core.storage.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val userPreferences: UserPreferences
) : Authenticator {

    override fun authenticate(
        route: Route?,
        response: Response
    ): Request? {

        if (
            response.request.url.encodedPath
                .contains("/auth/refresh")
        ) {
            return null
        }

        val refreshToken = runBlocking {
            userPreferences.getRefreshToken()
        } ?: return null

        return try {

            val refreshResponse = runBlocking {
                RetrofitProvider
                    .authApi
                    .refresh(
                        RefreshRequest(
                            refreshToken
                        )
                    )
            }

            if (!refreshResponse.isSuccessful) {

                runBlocking {
                    userPreferences.clearSession()
                }

                SessionManager.isAuthenticated.value =
                    false

                return null
            }

            val newAccessToken =
                refreshResponse.body()?.accessToken
                    ?: return null

            runBlocking {
                userPreferences.updateToken(
                    newAccessToken
                )
            }

            response.request
                .newBuilder()
                .header(
                    "Authorization",
                    "Bearer $newAccessToken"
                )
                .build()

        } catch (_: Exception) {

            runBlocking {
                userPreferences.clearSession()
            }

            SessionManager.isAuthenticated.value =
                false

            return null
        }
    }
}
package com.example.sneakers_admin_app.core.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sneakers_admin_app.core.models.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(
    name = "auth_preferences"
)
class UserPreferences(
    private val context: Context
) {
    companion object {
        private val ACCESS_TOKEN =
            stringPreferencesKey("jwt_token")
        private val REFRESH_TOKEN =
            stringPreferencesKey("refresh_token")
        private val USER =
            stringPreferencesKey("user")
    }

    suspend fun saveSession(
        accessToken: String,
        refreshToken: String,
        user: User
    ) {
        val userJson =
            Gson().toJson(user)

        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[USER] = userJson
        }
    }
    suspend fun getToken() : String? {
        return context.dataStore.data.first()[ACCESS_TOKEN]
    }

    suspend fun getRefreshToken(): String? {
        return context.dataStore.data.first()[REFRESH_TOKEN]
    }

    suspend fun updateToken(
        token: String
    ) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = token
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit {
            it.remove(ACCESS_TOKEN)
            it.remove(REFRESH_TOKEN)
            it.remove(USER)
        }
    }

    suspend fun getUser(): User? {

        val userJson =
            context.dataStore
                .data
                .first()[USER]

        return userJson?.let {
            Gson().fromJson(
                it,
                User::class.java
            )
        }
    }
}
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
        private val TOKEN =
            stringPreferencesKey("jwt_token")
        private val USER =
            stringPreferencesKey("user")
    }

    suspend fun saveSession(
        token: String,
        user: User
    ) {
        val userJson =
            Gson().toJson(user)

        context.dataStore.edit {

            it[TOKEN] = token
            it[USER] = userJson
        }
    }
    suspend fun getToken() : String? {
        return context.dataStore.data.first()[TOKEN]
    }

    suspend fun clearToken() {
        context.dataStore.edit {
            it.remove(TOKEN)
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
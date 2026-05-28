package com.example.sneakers_admin_app.core.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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
    }
    suspend fun saveToken(token: String) {

        context.dataStore.edit { preferences ->

            preferences[TOKEN] = token
        }
    }

    suspend fun getToken() : String? {
        val preferences = context.dataStore.data.first()
        return preferences[TOKEN]
    }

    suspend fun clearToken() {
        context.dataStore.edit {
            it.remove(TOKEN)
        }
    }
}
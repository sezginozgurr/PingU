package com.app.pingu.core.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend fun saveString(key: Preferences.Key<String>, value: String) {
        dataStoreManager.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun getString(key: Preferences.Key<String>): Flow<String?> {
        return dataStoreManager.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun saveInt(key: Preferences.Key<Int>, value: Int) {
        dataStoreManager.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun getInt(key: Preferences.Key<Int>): Flow<Int?> {
        return dataStoreManager.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun saveBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStoreManager.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean?> {
        return dataStoreManager.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun removeKey(key: Preferences.Key<*>) {
        dataStoreManager.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clearAll() {
        dataStoreManager.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

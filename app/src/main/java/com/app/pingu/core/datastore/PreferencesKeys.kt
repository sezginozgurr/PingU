package com.app.pingu.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {

    val IS_FIRST_RUN = booleanPreferencesKey("is_first_run")
    val IS_FIRST_Login = booleanPreferencesKey("is_first_login")
    val USER_ID = stringPreferencesKey("user_id")
    val THEME_MODE = intPreferencesKey("theme_mode")
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
} 
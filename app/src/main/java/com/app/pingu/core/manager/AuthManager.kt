package com.app.pingu.core.manager

interface AuthManager {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun savePhone(phoneNumber: String)
    fun getPhoneNumber(): String?
    fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken(): String?
    fun saveUserName(name: String)
    fun getUserName(): String?
    fun clearSession()
    fun clearAll()
}
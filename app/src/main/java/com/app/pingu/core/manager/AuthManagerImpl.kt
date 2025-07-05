package com.app.sitaxi.core.manager

import com.app.pingu.core.manager.AuthManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManagerImpl @Inject constructor(
    private val securePrefs: SecureSharedPrefs
) : AuthManager {

    override fun saveAccessToken(token: String) {
        securePrefs.save("access_token", token)
    }

    override fun getAccessToken(): String? {
        return securePrefs.get("access_token")
    }

    override fun savePhone(phoneNumber: String) {
        securePrefs.save("phone_number", phoneNumber)
    }

    override fun getPhoneNumber(): String? {
        return securePrefs.get("phone_number")
    }

    override fun saveRefreshToken(refreshToken: String) {
        securePrefs.save("refresh_token", refreshToken)
    }

    override fun getRefreshToken(): String? {
        return securePrefs.get("refresh_token")
    }

    override fun saveUserName(name: String) {
        securePrefs.save("user_name", name)
    }

    override fun getUserName(): String? {
        return securePrefs.get("user_name")
    }

    override fun clearSession() {
        securePrefs.remove("access_token") //key ile silmeli
    }

    override fun clearAll() {
        securePrefs.removeAll()
    }
}
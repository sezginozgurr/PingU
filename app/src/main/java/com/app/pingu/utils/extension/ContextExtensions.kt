package com.app.pingu.utils.extension

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.net.toUri
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import timber.log.Timber

fun Context.isAppInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.isGooglePlayServicesAvailable(): Boolean {
    return GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
}


fun Context.navigateToStore(packageName: String) {
    val newTaskFlag = Intent.FLAG_ACTIVITY_NEW_TASK
    val playStoreUri = "market://details?id=$packageName".toUri()
    val webUri = "https://play.google.com/store/apps/details?id=$packageName".toUri()

    val intent = Intent(Intent.ACTION_VIEW, playStoreUri).addFlags(newTaskFlag)

    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {

        val fallbackUri = if (isGooglePlayServicesAvailable()) webUri else null
        fallbackUri?.let {
            val fallbackIntent = Intent(Intent.ACTION_VIEW, it).addFlags(newTaskFlag)
            startActivity(fallbackIntent)
        }
    }
}

fun Context.copyPlainTextToClipboard(
    value: CharSequence
) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    if (clipboard != null) {
        clipboard.setPrimaryClip(ClipData.newPlainText(null, value))
        vibrateShort()
    } else {
        Timber.tag("ClipboardUtils").e("Clipboard service not available")
    }
}

fun Context.vibrateShort(durationMillis: Long = 50L) {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    vibrator?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            it.vibrate(
                VibrationEffect.createOneShot(
                    durationMillis,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            it.vibrate(durationMillis)
        }
    }
}

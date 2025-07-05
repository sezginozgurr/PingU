package com.app.pingu.utils.resource

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ResourceProvider(
    private val context: Context
) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    @Suppress("SpreadOperator")
    fun getString(resId: Int, vararg args: Any?): String {
        return context.getString(resId, *args)
    }

    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    fun getColor(resId: Int): Int {
        return context.resources.getColor(resId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return context.resources.getDrawable(resId)
    }

    fun getDimen(resId: Int): Float {
        return context.resources.getDimension(resId)
    }
}

package dev.lazygarde.lazy.preference

import android.content.SharedPreferences


open class LazyPreference(
    internal val preference: SharedPreferences,
    internal val editor: SharedPreferences.Editor
) {

    fun booleanPref(prefKey: String, defaultValue: Boolean): GenericPrefDelegate<Boolean> {
        return GenericPrefDelegate(prefKey, defaultValue, StoreType.Boolean)
    }

    fun floatPref(prefKey: String, defaultValue: Float): GenericPrefDelegate<Float> {
        return GenericPrefDelegate(prefKey, defaultValue, StoreType.Float)
    }

    fun intPref(prefKey: String, defaultValue: Int): GenericPrefDelegate<Int> {
        return GenericPrefDelegate(prefKey, defaultValue, StoreType.Int)
    }

    fun longPref(prefKey: String, defaultValue: Long): GenericPrefDelegate<Long> {
        return GenericPrefDelegate(prefKey, defaultValue, StoreType.Long)
    }

    fun stringPref(prefKey: String, defaultValue: String): GenericPrefDelegate<String> {
        return GenericPrefDelegate(prefKey, defaultValue, StoreType.String)
    }

}
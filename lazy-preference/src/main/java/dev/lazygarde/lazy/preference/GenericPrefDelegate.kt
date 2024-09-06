package dev.lazygarde.lazy.preference

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class GenericPrefDelegate<T>(
    private val key: String,
    private val defaultValue: T,
    private val type: StoreType
) : ReadWriteProperty<LazyPreference, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: LazyPreference, property: KProperty<*>): T {
        return when (type) {
            is StoreType.Boolean -> thisRef.preference.getBoolean(key, defaultValue as Boolean) as T
            is StoreType.Float -> thisRef.preference.getFloat(key, defaultValue as Float) as T
            is StoreType.Int -> thisRef.preference.getInt(key, defaultValue as Int) as T
            is StoreType.Long -> thisRef.preference.getLong(key, defaultValue as Long) as T
            is StoreType.String -> thisRef.preference.getString(key, defaultValue as String) as T
        }
    }

    override fun setValue(thisRef: LazyPreference, property: KProperty<*>, value: T) {
        with(thisRef.editor) {
            when (type) {
                is StoreType.Boolean -> putBoolean(key, value as Boolean)
                is StoreType.Float -> putFloat(key, value as Float)
                is StoreType.Int -> putInt(key, value as Int)
                is StoreType.Long -> putLong(key, value as Long)
                is StoreType.String -> putString(key, value as String)
            }
            apply()
        }
    }

}
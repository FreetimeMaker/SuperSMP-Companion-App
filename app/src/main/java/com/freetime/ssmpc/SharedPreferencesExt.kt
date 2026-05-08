package com.freetime.ssmpc

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> SharedPreferences.collectAsState(key: String, defaultValue: T): State<T> {
    val state = remember {
        mutableStateOf(
            when (defaultValue) {
                is Boolean -> getBoolean(key, defaultValue) as T
                is Float -> getFloat(key, defaultValue) as T
                is Int -> getInt(key, defaultValue) as T
                is Long -> getLong(key, defaultValue) as T
                is String -> getString(key, defaultValue) as? T ?: defaultValue
                else -> defaultValue
            }
        )
    }

    DisposableEffect(key) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, changedKey ->
            if (key == changedKey) {
                state.value = when (defaultValue) {
                    is Boolean -> prefs.getBoolean(key, defaultValue) as T
                    is Float -> prefs.getFloat(key, defaultValue) as T
                    is Int -> prefs.getInt(key, defaultValue) as T
                    is Long -> prefs.getLong(key, defaultValue) as T
                    is String -> prefs.getString(key, defaultValue) as? T ?: defaultValue
                    else -> defaultValue
                }
            }
        }
        registerOnSharedPreferenceChangeListener(listener)
        onDispose {
            unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
    return state
}

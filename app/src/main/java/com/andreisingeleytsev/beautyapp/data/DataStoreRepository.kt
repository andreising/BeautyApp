package com.andreisingeleytsev.beautyapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "beauty_app_pref")

class DataStoreRepository(context: Context) {

    private object PreferencesKeys {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val favouritesKey = stringSetPreferencesKey(name = "favourites")
    }

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.onBoardingKey] = completed
        }
    }
    suspend fun saveFavourites(strings: Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.favouritesKey] = strings
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKeys.onBoardingKey] ?: false
                onBoardingState
            }
    }
    fun readFavourites(): Flow<Set<String>> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKeys.favouritesKey] ?: emptySet()
                onBoardingState
            }
    }
}
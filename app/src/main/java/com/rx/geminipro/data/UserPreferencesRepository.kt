package com.rx.geminipro.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

class UserPreferencesRepository(context: Context) {

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val IS_MENU_LEFT = booleanPreferencesKey("is_menu_left")
        val SKIPPED_VERSION = stringPreferencesKey("skipped_update_version")
        val LAST_UPDATE_CHECK_TIME = longPreferencesKey("last_update_check_time")
    }

    val isMenuLeftFlow: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.IS_MENU_LEFT] ?: false }
    val skippedVersionFlow: Flow<String?> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.SKIPPED_VERSION] }
    val lastUpdateCheckTimeFlow: Flow<Long> = dataStore.data
        .map { preferences -> preferences[PreferencesKeys.LAST_UPDATE_CHECK_TIME] ?: 0L }

    suspend fun saveMenuPosition(isLeft: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_MENU_LEFT] = isLeft
        }
    }
    suspend fun setSkippedVersion(version: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SKIPPED_VERSION] = version
        }
    }
    suspend fun saveLastUpdateCheckTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_UPDATE_CHECK_TIME] = time
        }
    }
}
package com.noby.core.data_store

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.noby.core.R


class EncryptedPref (applicationContext: Context) {
    private var masterKey = MasterKey.Builder(applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private var sharedPreferences = EncryptedSharedPreferences.create(
        applicationContext,
        applicationContext.getString(com.foundation.datastore.R.string.app_name),
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private var editor = sharedPreferences.edit()

    fun putString(key: String,value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    private fun putInt( key: String,value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    private fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    fun putBoolean( key: String,value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String, def: String): String? {
        return sharedPreferences.getString(key, def)
    }

    fun getInt(key: String, def: Int): Int {
        return sharedPreferences.getInt(key, def)
    }

    fun getLong(key: String, def: Long): Long {
        return sharedPreferences.getLong(key, def)
    }

    fun getBoolean(key: String, def: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, def)
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

}
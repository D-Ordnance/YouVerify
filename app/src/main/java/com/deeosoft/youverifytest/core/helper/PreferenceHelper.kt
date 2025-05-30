package com.deeosoft.youverifytest.core.helper

import android.content.Context

object PreferenceHelper {
    private const val PREF_NAME = "you_verify_prefs"
    private const val SEEN_PAGER = "seen_pager"
    private const val NAME = "name"
    private const val EMAIL = "email"

    fun hasSeenPager(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(SEEN_PAGER, false)
    }

    fun setSeenPager(context: Context, seen: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(SEEN_PAGER, seen).apply()
    }

    fun getName(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(NAME, "") ?: ""
    }

    fun setName(context: Context, name: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(NAME, name).apply()
    }

    fun getEmail(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(EMAIL, "") ?: ""
    }

    fun setEmail(context: Context, email: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(EMAIL, email).apply()
    }
}
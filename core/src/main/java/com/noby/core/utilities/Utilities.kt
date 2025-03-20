package com.noby.core.utilities

import android.content.Context
import java.util.Locale

fun Context.setAppLocale(language: String = "ar"): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}
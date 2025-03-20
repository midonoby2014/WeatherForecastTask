package com.noby.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(output: String = "dd/MM/yyyy"): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("en"))
    val outputFormat = SimpleDateFormat(output, Locale("en"))

    try {
        val date = inputFormat.parse(this)
        return date?.let { outputFormat.format(it) }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}
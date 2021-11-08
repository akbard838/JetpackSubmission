package com.dicoding.jetpacksubmission.utils

import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.dicoding.jetpacksubmission.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val POSTER_ENDPOINT = "https://image.tmdb.org/t/p/"

const val POSTER_SIZE_ENDPOINT_W185 = "w185"

fun emptyString() = ""

fun View.onClick(@NonNull listener: (() -> Unit)? = null) {
    this.setOnClickListener {
        listener?.invoke()
    }
}

fun showToast(message: String) {
    Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_SHORT).show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun String.changeDateFormat(currentFormat: String, newFormat: String, locale: Locale = Locale.getDefault()): String {
    val currentDateFormat = SimpleDateFormat(currentFormat, locale)
    currentDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val newDateFormat = SimpleDateFormat(newFormat, locale)
    return try {
        val date = currentDateFormat.parse(this) ?: Date()
        newDateFormat.format(date)
    } catch (e: Exception) {
        ContextProvider.get().getString(R.string.label_dash)
    }
}

fun changeDate(time: String, currentFormat: String, newFormat: String): String? {
    val inputFormat = SimpleDateFormat(currentFormat)
    val outputFormat = SimpleDateFormat(newFormat)
    var str: String? = null
    try {
        val date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return str
}
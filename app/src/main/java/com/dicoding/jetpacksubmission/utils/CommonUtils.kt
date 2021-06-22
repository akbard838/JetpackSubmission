package com.dicoding.jetpacksubmission.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.dicoding.jetpacksubmission.R
import java.text.SimpleDateFormat
import java.util.*

const val POSTER_ENDPOINT = "https://image.tmdb.org/t/p/"

const val POSTER_SIZE_ENDPOINT_W185 = "w185"

const val POSTER_SIZE_ENDPOINT_W780 = "w780"

fun emptyString() = ""

fun View.onClick(@NonNull listener: (() -> Unit)? = null) {
    this.setOnClickListener {
        listener?.invoke()
    }
}

fun showToast(message: String) {
    Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_SHORT).show()
}

fun showLongToast(message: String) {
    Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_LONG).show()
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

@RequiresApi(Build.VERSION_CODES.M)
fun isNetworkAvailable(context: Context) =
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        getNetworkCapabilities(activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }

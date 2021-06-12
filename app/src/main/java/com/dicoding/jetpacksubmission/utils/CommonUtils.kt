package com.dicoding.jetpacksubmission.utils

import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull

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

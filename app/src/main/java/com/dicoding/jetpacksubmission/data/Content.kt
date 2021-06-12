package com.dicoding.jetpacksubmission.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val id: String,
    val title: String,
    val poster: Int,
    val overview: String,
    val year: String,
    val rating: Double,
    val genre: String
) : Parcelable
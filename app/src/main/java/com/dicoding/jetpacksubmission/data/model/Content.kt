package com.dicoding.jetpacksubmission.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val year: String,
    val rating: Double
) : Parcelable
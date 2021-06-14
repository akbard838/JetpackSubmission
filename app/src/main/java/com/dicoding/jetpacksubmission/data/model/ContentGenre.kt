package com.dicoding.jetpacksubmission.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentGenre(
    val id: Int,
    val genreName: String
): Parcelable
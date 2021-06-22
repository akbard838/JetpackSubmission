package com.dicoding.jetpacksubmission.data.model

import android.os.Parcelable
import com.dicoding.jetpacksubmission.utils.emptyString
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentGenre(
    val id: Int = 0,
    val genreName: String = emptyString()
): Parcelable
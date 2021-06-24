package com.dicoding.jetpacksubmission.data.model

import android.os.Parcelable
import com.dicoding.jetpacksubmission.data.model.response.GenreItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val year: String,
    val rating: Double,
    val genre: List<GenreItem>
) : Parcelable
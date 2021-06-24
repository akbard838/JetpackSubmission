package com.dicoding.jetpacksubmission.data.model.response

import android.os.Parcelable
import com.dicoding.jetpacksubmission.data.model.ContentGenre
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreItem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val genreName: String?
): Parcelable {
    fun toContentGenre(): ContentGenre {
        return ContentGenre(id ?: 0, genreName.orEmpty())
    }
}
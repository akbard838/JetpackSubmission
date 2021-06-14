package com.dicoding.jetpacksubmission.data.model.response

import com.dicoding.jetpacksubmission.data.model.ContentGenre
import com.google.gson.annotations.SerializedName

data class GenreItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val genreName: String?
) {
    fun toContentGenre(): ContentGenre {
        return ContentGenre(id, genreName.orEmpty())
    }
}
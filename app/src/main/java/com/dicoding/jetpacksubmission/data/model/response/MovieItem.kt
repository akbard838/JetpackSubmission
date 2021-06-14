package com.dicoding.jetpacksubmission.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("genres")
    val genres: List<GenreItem>?
)
package com.dicoding.jetpacksubmission.data.model.response

import com.google.gson.annotations.SerializedName

data class TvShowItem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("first_air_date")
    val releaseDate: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("genres")
    val genres: List<GenreItem>?
)
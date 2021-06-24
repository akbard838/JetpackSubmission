package com.dicoding.jetpacksubmission.data.model.response

import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.data.local.TvShowEntity
import com.dicoding.jetpacksubmission.data.model.Content
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
) {
    fun toContent(): Content {
        return Content(
            id = id ?: 0,
            title = name.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            year = releaseDate.orEmpty(),
            rating = voteAverage ?: 0.0,
            genre = genres?.map { it.toContentGenre() } ?: listOf()
        )
    }

    fun toEntity(): TvShowEntity {
        return TvShowEntity(
            id = id ?: 0,
            title = name.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            year = releaseDate.orEmpty(),
            rating = voteAverage ?: 0.0,
            genre = genres?.map { it.toContentGenre() } ?: listOf()
        )
    }
}
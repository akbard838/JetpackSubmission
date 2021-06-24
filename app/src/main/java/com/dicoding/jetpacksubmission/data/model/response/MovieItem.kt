package com.dicoding.jetpacksubmission.data.model.response

import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.data.model.Content

data class MovieItem(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val overview: String?,
    val voteAverage: Double?
) {
    fun toContent(): Content {
        return Content(
            id = id ?: 0,
            title = title.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            year = releaseDate.orEmpty(),
            rating = voteAverage ?: 0.0
        )
    }

    fun toEntity(): MovieEntity {
        return MovieEntity(
            id = id ?: 0,
            title = title.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            year = releaseDate.orEmpty(),
            rating = voteAverage ?: 0.0
        )
    }
}
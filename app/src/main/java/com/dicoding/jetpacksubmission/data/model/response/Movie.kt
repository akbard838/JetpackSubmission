package com.dicoding.jetpacksubmission.data.model.response

import com.dicoding.jetpacksubmission.data.db.model.MovieEntity

data class Movie(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val overview: String?,
    val voteAverage: Double?
) {

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
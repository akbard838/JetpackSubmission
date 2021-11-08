package com.dicoding.jetpacksubmission.data.model.response

import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity

data class TvShow(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val overview: String?,
    val voteAverage: Double?,
    val posterPath: String?
) {

    fun toEntity(): TvShowEntity {
        return TvShowEntity(
            id = id ?: 0,
            title = title.orEmpty(),
            poster = posterPath.orEmpty(),
            overview = overview.orEmpty(),
            year = releaseDate.orEmpty(),
            rating = voteAverage ?: 0.0
        )
    }
}
package com.dicoding.jetpacksubmission.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.data.model.ContentGenre
import com.dicoding.jetpacksubmission.utils.emptyString

@Entity
data class TvShowEntity(
    @PrimaryKey
    val id: Int? = 0,
    val title: String? = emptyString(),
    val poster: String? = emptyString(),
    val overview: String? = emptyString(),
    val year: String? = emptyString(),
    val rating: Double? = 0.0,
    var isFavorite: Boolean = false
) {
    fun toContent(): Content {
        return Content(
            id = id ?: 0,
            title = title.orEmpty(),
            poster = poster.orEmpty(),
            overview = overview.orEmpty(),
            year = year.orEmpty(),
            rating = rating ?: 0.0
        )
    }
}
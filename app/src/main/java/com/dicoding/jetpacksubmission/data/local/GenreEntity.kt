package com.dicoding.jetpacksubmission.data.local

import androidx.room.Entity
import com.dicoding.jetpacksubmission.data.model.response.GenreItem
import com.dicoding.jetpacksubmission.utils.emptyString

@Entity
data class GenreEntity(
    val id: Int = 0,
    val genreName: String = emptyString()
) {
    fun toGenre(): GenreItem {
        return GenreItem(
            id = id,
            genreName = genreName
        )
    }
}
package com.dicoding.jetpacksubmission.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.jetpacksubmission.data.model.ContentGenre
import com.dicoding.jetpacksubmission.utils.emptyString

@Entity
data class ContentEntity(
    @PrimaryKey
    val id: Int? = 0,
    val title: String? = emptyString(),
    val poster: String? = emptyString(),
    val overview: String? = emptyString(),
    val year: String? = emptyString(),
    val rating: Double? = 0.0,
    val genre: List<ContentGenre>? = listOf(),
    var isFavorite: Boolean = false
)
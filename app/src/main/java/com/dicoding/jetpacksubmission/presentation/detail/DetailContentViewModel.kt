package com.dicoding.jetpacksubmission.presentation.detail

import androidx.lifecycle.ViewModel
import com.dicoding.jetpacksubmission.data.Content
import com.dicoding.jetpacksubmission.utils.DummyData
import com.dicoding.jetpacksubmission.utils.enum.ContentType

class DetailContentViewModel: ViewModel() {

    private lateinit var content: Content

    fun getDetailContent() = content

    fun setSelectedContent(id: String, type: String) {
        when (type) {
            ContentType.TV_SHOW.type -> {
                for (tvShow in DummyData.getTvShows()) {
                    if (tvShow.id == id) content = tvShow
                }
            }
            ContentType.MOVIE.type -> {
                for (movie in DummyData.getMovies()) {
                    if (movie.id == id) content = movie
                }
            }
        }
    }

}
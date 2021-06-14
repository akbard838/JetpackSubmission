package com.dicoding.jetpacksubmission.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.model.Content

class MovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovies(): LiveData<List<Content>> = contentRepository.getMovies()

    fun getDetailMovie(movieId: Int): LiveData<Content> = contentRepository.getDetailMovie(movieId)

}
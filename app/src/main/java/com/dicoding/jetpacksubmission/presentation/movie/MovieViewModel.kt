package com.dicoding.jetpacksubmission.presentation.movie

import androidx.lifecycle.ViewModel
import com.dicoding.jetpacksubmission.utils.DummyData

class MovieViewModel: ViewModel() {
    fun getMovies() = DummyData.getMovies()
}
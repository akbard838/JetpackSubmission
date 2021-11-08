package com.dicoding.jetpacksubmission.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity

class MovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    var detailMovie: LiveData<BaseResource<MovieEntity>> = Transformations.switchMap(movieId) {
        contentRepository.getMovieById(it)
    }

    fun getMovies(): LiveData<BaseResource<PagedList<MovieEntity>>> = contentRepository.getMovies()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = contentRepository.getFavoriteMovie()

    fun getSearchMovie(text: String): LiveData<PagedList<MovieEntity>> = contentRepository.getSearchMovie(text)

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setFavoriteMovie() {
        val movieResource = detailMovie.value
        if (movieResource != null) {
            val movie = movieResource.data

            if (movie != null) {
                val newState = !movie.isFavorite
                contentRepository.setFavoriteMovie(movie, newState)
            }
        }
    }

}

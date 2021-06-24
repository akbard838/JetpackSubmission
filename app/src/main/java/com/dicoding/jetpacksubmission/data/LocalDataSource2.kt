package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jetpacksubmission.data.db.ContentDao2
import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.data.local.TvShowEntity

class LocalDataSource2 private constructor(private val contentDao: ContentDao2) {

    companion object {
        private var INSTANCE: LocalDataSource2? = null

        fun getInstance(contentDao: ContentDao2): LocalDataSource2 =
            INSTANCE ?: LocalDataSource2(contentDao).apply {
                INSTANCE = this
            }

    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getMovies()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
        contentDao.getFavoriteMovies()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> =
        contentDao.getMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) {
        contentDao.insertMovie(movies)
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        contentDao.updateMovie(movie)
    }

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = contentDao.getTvShows()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> =
        contentDao.getFavoriteTvShows()

    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity> =
        contentDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows: List<TvShowEntity>) {
        contentDao.insertTvShow(tvShows)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        contentDao.updateTvShow(tvShow)
    }

}
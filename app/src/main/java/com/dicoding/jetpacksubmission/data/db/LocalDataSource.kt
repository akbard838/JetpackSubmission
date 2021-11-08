package com.dicoding.jetpacksubmission.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity

class LocalDataSource private constructor(private val contentDao: ContentDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            INSTANCE
                ?: LocalDataSource(contentDao).apply {
                INSTANCE = this
            }

    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getMovies()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
        contentDao.getFavoriteMovies()

    fun getSearchMovie(text: String): DataSource.Factory<Int, MovieEntity> =
        contentDao.getSearchMovie(text)

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

    fun getSearchTvShow(text: String): DataSource.Factory<Int, TvShowEntity> =
        contentDao.getSearchTvShow(text)

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
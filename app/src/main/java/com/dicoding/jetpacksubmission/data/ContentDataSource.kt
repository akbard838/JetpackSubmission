package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity

interface ContentDataSource {

    fun getMovies(): LiveData<BaseResource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<BaseResource<MovieEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getSearchMovie(text: String): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)


    fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>>

    fun getTvShowById(tvShowId: Int): LiveData<BaseResource<TvShowEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getSearchTvShow(text: String): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

}
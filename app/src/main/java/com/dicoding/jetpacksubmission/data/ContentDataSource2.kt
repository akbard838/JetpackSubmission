package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.data.local.TvShowEntity

interface ContentDataSource2 {

    fun getMovies(): LiveData<BaseResource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<BaseResource<MovieEntity>>

//    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    //////////////////////////

    fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>>

    fun getTvShowById(tvShowId: Int): LiveData<BaseResource<TvShowEntity>>

//    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

}
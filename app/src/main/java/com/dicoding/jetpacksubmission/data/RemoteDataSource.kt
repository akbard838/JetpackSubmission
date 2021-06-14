package com.dicoding.jetpacksubmission.data

import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import com.dicoding.jetpacksubmission.data.remote.ApiClient
import com.dicoding.jetpacksubmission.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovies(
        callback: GetMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getMovies().await().results?.let { data ->
            callback.onGetMoviesSucceed(
                data
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovie(movieId: Int, callback: GetDetailMovieCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovie(movieId).await().let { movie ->
            callback.onDetailMovieReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShows(callback: GetTvShowsCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvShows().await().results?.let { data ->
            callback.onGetTvShowsSucceed(
                data
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailTvShow(tvShowId: Int, callback: GetDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onDetailTvShowReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface GetMoviesCallback {
        fun onGetMoviesSucceed(movieItems: List<MovieItem>)
    }

    interface GetDetailMovieCallback {
        fun onDetailMovieReceived(movieItem: MovieItem)
    }

    interface GetTvShowsCallback {
        fun onGetTvShowsSucceed(tvShowItems: List<TvShowItem>)
    }

    interface GetDetailTvShowCallback {
        fun onDetailTvShowReceived(tvShowItem: TvShowItem)
    }

}
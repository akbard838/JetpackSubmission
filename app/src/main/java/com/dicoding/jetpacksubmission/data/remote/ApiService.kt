package com.dicoding.jetpacksubmission.data.remote

import com.dicoding.jetpacksubmission.BuildConfig
import com.dicoding.jetpacksubmission.data.model.response.ListMovieItem
import com.dicoding.jetpacksubmission.data.model.response.ListTvShowItem
import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<ListMovieItem>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<MovieItem>

    @GET("tv/on_the_air")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<ListTvShowItem>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<TvShowItem>

}
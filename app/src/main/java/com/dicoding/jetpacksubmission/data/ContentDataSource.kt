package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import com.dicoding.jetpacksubmission.data.model.Content

interface ContentDataSource {

    fun getMovies(): LiveData<List<Content>>

    fun getDetailMovie(movieId: Int): LiveData<Content>

    fun getTvShows(): LiveData<List<Content>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Content>

}
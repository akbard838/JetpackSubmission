package com.dicoding.jetpacksubmission.data.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpacksubmission.base.BaseApiResponse
import com.dicoding.jetpacksubmission.data.model.response.Movie
import com.dicoding.jetpacksubmission.data.model.response.TvShow
import com.dicoding.jetpacksubmission.utils.EspressoIdlingResource
import com.dicoding.jetpacksubmission.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance
                ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<BaseApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<BaseApiResponse<List<Movie>>>()
        handler.postDelayed({
            resultMovies.value = BaseApiResponse.success(jsonHelper.getMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }

    fun getTvShows(): LiveData<BaseApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<BaseApiResponse<List<TvShow>>>()
        handler.postDelayed({
            resultTvShows.value = BaseApiResponse.success(jsonHelper.getTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShows
    }
}


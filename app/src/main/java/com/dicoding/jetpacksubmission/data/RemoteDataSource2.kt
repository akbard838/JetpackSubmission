package com.dicoding.jetpacksubmission.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpacksubmission.base.BaseApiResponse
import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import com.dicoding.jetpacksubmission.utils.EspressoIdlingResource
import com.dicoding.jetpacksubmission.utils.JsonHelper

class RemoteDataSource2 private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource2? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource2 =
            instance ?: synchronized(this) {
                RemoteDataSource2(helper).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<BaseApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<BaseApiResponse<List<MovieItem>>>()
        handler.postDelayed({
            resultMovies.value = BaseApiResponse.success(jsonHelper.getMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }

    fun getTvShows(): LiveData<BaseApiResponse<List<TvShowItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<BaseApiResponse<List<TvShowItem>>>()
        handler.postDelayed({
            resultTvShows.value = BaseApiResponse.success(jsonHelper.getTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShows
    }
}


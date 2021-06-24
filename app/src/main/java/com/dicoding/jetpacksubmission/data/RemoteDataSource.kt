//package com.dicoding.jetpacksubmission.data
//
//import android.os.Handler
//import android.os.Looper
//import com.dicoding.jetpacksubmission.data.model.response.MovieItem
//import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
//import com.dicoding.jetpacksubmission.utils.EspressoIdlingResource
//import com.dicoding.jetpacksubmission.utils.JsonHelper
//
//class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
//
//    private val handler = Handler(Looper.getMainLooper())
//
//    companion object {
//        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
//
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(helper: JsonHelper): RemoteDataSource =
//            instance ?: synchronized(this) {
//                RemoteDataSource(helper).apply { instance = this }
//            }
//    }
//
//    fun getMovies(getMoviesCallback: GetMoviesCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            getMoviesCallback.onGetMoviesSucceed(jsonHelper.getMovies())
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getMovieById(movieId: Int?, getMovieByIdByIdCallback: GetMovieByIdCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            movieId?.let { jsonHelper.getMovieById(it) }?.let { getMovieByIdByIdCallback.onGetMovieByIdSucceed(it) }
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getTvShows(getTvShowsCallback: GetTvShowsCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            getTvShowsCallback.onGetTvShowsSucceed(jsonHelper.getTvShowList())
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getTvShowById(tvShowId: Int?, getTvShowByIdCallback: GetTvShowByIdCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            tvShowId?.let { jsonHelper.getTvShowById(it) }?.let { getTvShowByIdCallback.onGetTvShowByIdSucceed(it) }
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    interface GetMoviesCallback {
//        fun onGetMoviesSucceed(movieItems: List<MovieItem>)
//    }
//
//    interface GetMovieByIdCallback {
//        fun onGetMovieByIdSucceed(movieItem: MovieItem)
//    }
//
//    interface GetTvShowsCallback {
//        fun onGetTvShowsSucceed(tvShowItems: List<TvShowItem>)
//    }
//
//    interface GetTvShowByIdCallback {
//        fun onGetTvShowByIdSucceed(tvShowItem: TvShowItem)
//    }
//
//}
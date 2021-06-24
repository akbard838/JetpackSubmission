//package com.dicoding.jetpacksubmission.data
//
//import androidx.lifecycle.LiveData
//import androidx.paging.DataSource
//import com.dicoding.jetpacksubmission.data.db.ContentDao
//import com.dicoding.jetpacksubmission.data.local.ContentEntity
//
//open class LocalDataSource constructor(private val catalogueDao: ContentDao) {
//
//    companion object {
//        private var INSTANCE: LocalDataSource? = null
//
//        fun getInstance(catalogueDao: ContentDao): LocalDataSource {
//            if (INSTANCE == null) {
//                INSTANCE =
//                    LocalDataSource(catalogueDao)
//            }
//            return INSTANCE as LocalDataSource
//        }
//    }
//
//    //Start Movie Function
//    fun getMovies(): LiveData<List<ContentEntity>> = catalogueDao.getMovies()
//
//    fun getMovieAsPaged(): DataSource.Factory<Int, ContentEntity> {
//        return catalogueDao.getMovieAsPaged()
//    }
//
//    fun getMovieById(movieId: Int): LiveData<ContentEntity> = catalogueDao.getMovieById(movieId)
//
//    fun saveMovies(movies: List<ContentEntity>) {
//        catalogueDao.saveMovies(movies)
//    }
//
//    fun setFavoriteMovie(movie: ContentEntity, isFavorite: Boolean) {
//        movie.isFavorite = isFavorite
//        catalogueDao.updateMovie(movie)
//    }
//    //End Movie Function
//
//    //Start TvShow Function
//    fun getTvShows(): LiveData<List<ContentEntity>> = catalogueDao.getTvShows()
//
//    fun getTvShowAsPaged(): DataSource.Factory<Int, ContentEntity> {
//        return catalogueDao.getTvShowAsPaged()
//    }
//
//    fun getTvShowById(tvShowId: Int): LiveData<ContentEntity> = catalogueDao.getTvShowById(tvShowId)
//
//    fun saveTvShows(tvShows: List<ContentEntity>) {
//        catalogueDao.insertTvShow(tvShows)
//    }
//
//    fun setFavoriteTvShow(tvShow: ContentEntity, isFavorite: Boolean) {
//        tvShow.isFavorite = isFavorite
//        catalogueDao.updateTvShow(tvShow)
//    }
//    //End TvShow Function
//
//}
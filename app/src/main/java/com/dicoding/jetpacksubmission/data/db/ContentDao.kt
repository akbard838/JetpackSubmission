//package com.dicoding.jetpacksubmission.data.db
//
//import androidx.annotation.WorkerThread
//import androidx.lifecycle.LiveData
//import androidx.paging.DataSource
//import androidx.room.*
//import com.dicoding.jetpacksubmission.data.local.ContentEntity
//
//@Dao
//interface ContentDao {
//
//    //Movie Function
//    @WorkerThread
//    @Query("SELECT * FROM contententity where isFavorite = 1")
//    fun getMovies(): LiveData<List<ContentEntity>>
//
//    @Query("SELECT * FROM contententity where isFavorite = 1")
//    fun getMovieAsPaged(): DataSource.Factory<Int, ContentEntity>
//
//    @Transaction
//    @Query("SELECT * FROM contententity WHERE id = :movieId")
//    fun getMovieById(movieId: Int): LiveData<ContentEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveMovies(movie: List<ContentEntity>): LongArray
//
//    @Update(onConflict = OnConflictStrategy.FAIL)
//    fun updateMovie(movie: ContentEntity): Int
//    //End Movie Function
//
//    //TvShow Function
//    @WorkerThread
//    @Query("SELECT * FROM contententity where isFavorite = 1")
//    fun getTvShows(): LiveData<List<ContentEntity>>
//
//    @Query("SELECT * FROM contententity where isFavorite = 1")
//    fun getTvShowAsPaged(): DataSource.Factory<Int, ContentEntity>
//
//    @Transaction
//    @Query("SELECT * FROM contententity WHERE id = :tvShowId")
//    fun getTvShowById(tvShowId: Int): LiveData<ContentEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTvShow(tvShow: List<ContentEntity>): LongArray
//
//    @Update(onConflict = OnConflictStrategy.FAIL)
//    fun updateTvShow(tvShow: ContentEntity): Int
//    //End TvShow Function
//
//}
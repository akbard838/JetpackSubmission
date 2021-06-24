package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseApiResponse
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.data.local.TvShowEntity
import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import com.dicoding.jetpacksubmission.utils.AppExecutors

class ContentRepository2 private constructor(
    private val remoteDataSource: RemoteDataSource2,
    private val localDataSource: LocalDataSource2,
    private val appExecutors: AppExecutors
) : ContentDataSource2 {

    companion object {
        @Volatile
        private var instance: ContentRepository2? = null

        fun getInstance(
            remoteData: RemoteDataSource2,
            localData: LocalDataSource2,
            appExecutors: AppExecutors
        ): ContentRepository2 =
            instance ?: synchronized(this) {
                ContentRepository2(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    //Start Movie Function
    override fun getMovies(): LiveData<BaseResource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<BaseApiResponse<List<MovieItem>>> =
                remoteDataSource.getMovies()

            public override fun saveCallResult(data: List<MovieItem>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data) {
                    movies.add(response.toEntity())
                }

                localDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<BaseResource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieItem>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = false

            override fun createCall(): LiveData<BaseApiResponse<MovieItem>>? = null

            override fun saveCallResult(data: MovieItem) {

            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    //End Movie Function

    //Start TvShow Function
    override fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<BaseApiResponse<List<TvShowItem>>> =
                remoteDataSource.getTvShows()

            public override fun saveCallResult(data: List<TvShowItem>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data) {
                    tvShows.add(response.toEntity())
                }

                localDataSource.insertTvShows(tvShows)
            }
        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<BaseResource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowItem>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = false

            override fun createCall(): LiveData<BaseApiResponse<TvShowItem>>? = null

            override fun saveCallResult(data: TvShowItem) {

            }
        }.asLiveData()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }
    //End TvShow Function

}
package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseApiResponse
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.db.LocalDataSource
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
import com.dicoding.jetpacksubmission.data.model.response.Movie
import com.dicoding.jetpacksubmission.data.model.response.TvShow
import com.dicoding.jetpacksubmission.data.remote.RemoteDataSource
import com.dicoding.jetpacksubmission.utils.AppExecutors

class FakeContentRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ContentDataSource {

    //Start Movie Function
    override fun getMovies(): LiveData<BaseResource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
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

            public override fun createCall(): LiveData<BaseApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            public override fun saveCallResult(data: List<Movie>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data) {
                    movies.add(response.toEntity())
                }

                localDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<BaseResource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = false

            override fun createCall(): LiveData<BaseApiResponse<Movie>>? = null

            override fun saveCallResult(data: Movie) {

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

    override fun getSearchMovie(text: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getSearchMovie(text), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    //End Movie Function

    //Start TvShow Function
    override fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
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

            public override fun createCall(): LiveData<BaseApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShows()

            public override fun saveCallResult(data: List<TvShow>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data) {
                    tvShows.add(response.toEntity())
                }

                localDataSource.insertTvShows(tvShows)
            }
        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<BaseResource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = false

            override fun createCall(): LiveData<BaseApiResponse<TvShow>>? = null

            override fun saveCallResult(data: TvShow) {

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

    override fun getSearchTvShow(text: String): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getSearchTvShow(text), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }
    //End TvShow Function

}
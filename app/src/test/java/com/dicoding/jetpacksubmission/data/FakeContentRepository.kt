package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.data.model.response.MovieItem
import com.dicoding.jetpacksubmission.data.model.response.TvShowItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FakeContentRepository(private val remoteDataSource: RemoteDataSource) : ContentDataSource {

    override fun getMovies(): LiveData<List<Content>> {
        val moviesResult = MutableLiveData<List<Content>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovies(object : RemoteDataSource.GetMoviesCallback {
                override fun onGetMoviesSucceed(movieItems: List<MovieItem>) {
                    val movies = ArrayList<Content>()
                    for (response in movieItems) {
                        val movie = Content(
                            id = response.id ?: 0,
                            title = response.title.orEmpty(),
                            poster = response.posterPath.orEmpty(),
                            overview = response.overview.orEmpty(),
                            year = response.releaseDate.orEmpty(),
                            rating = response.voteAverage ?: 0.0,
                            genre = response.genres?.map { it.toContentGenre() } ?: listOf()
                        )
                        movies.add(movie)
                    }
                    moviesResult.postValue(movies)
                }
            })
        }
        return moviesResult
    }

    override fun getDetailMovie(movieId: Int): LiveData<Content> {
        val movieResult = MutableLiveData<Content>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.GetDetailMovieCallback {
                override fun onDetailMovieReceived(movieItem: MovieItem) {
                    val movie = Content(
                        id = movieItem.id ?: 0,
                        title = movieItem.title.orEmpty(),
                        poster = movieItem.posterPath.orEmpty(),
                        overview = movieItem.overview.orEmpty(),
                        year = movieItem.releaseDate.orEmpty(),
                        rating = movieItem.voteAverage ?: 0.0,
                        genre = movieItem.genres?.map { it.toContentGenre() } ?: listOf()
                    )

                    movieResult.postValue(movie)
                }
            })
        }
        return movieResult
    }

    override fun getTvShows(): LiveData<List<Content>> {
        val tvShowsResult = MutableLiveData<List<Content>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShows(object : RemoteDataSource.GetTvShowsCallback {
                override fun onGetTvShowsSucceed(tvShowItems: List<TvShowItem>) {
                    val tvShows = ArrayList<Content>()
                    for (response in tvShowItems) {
                        val tvShow = Content(
                            id = response.id ?: 0,
                            title = response.name.orEmpty(),
                            poster = response.posterPath.orEmpty(),
                            overview = response.overview.orEmpty(),
                            year = response.releaseDate.orEmpty(),
                            rating = response.voteAverage ?: 0.0,
                            genre = response.genres?.map { it.toContentGenre() } ?: listOf()
                        )
                        tvShows.add(tvShow)
                    }

                    tvShowsResult.postValue(tvShows)
                }
            })
        }
        return tvShowsResult
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Content> {
        val tvShowResult = MutableLiveData<Content>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTvShow(tvShowId, object : RemoteDataSource.GetDetailTvShowCallback {
                override fun onDetailTvShowReceived(tvShowItem: TvShowItem) {
                    val tvShow = Content(
                        id = tvShowItem.id ?: 0,
                        title = tvShowItem.name.orEmpty(),
                        poster = tvShowItem.posterPath.orEmpty(),
                        overview = tvShowItem.overview.orEmpty(),
                        year = tvShowItem.releaseDate.orEmpty(),
                        rating = tvShowItem.voteAverage ?: 0.0,
                        genre = tvShowItem.genres?.map { it.toContentGenre() } ?: listOf()
                    )
                    tvShowResult.postValue(tvShow)
                }
            })
        }
        return tvShowResult
    }
}
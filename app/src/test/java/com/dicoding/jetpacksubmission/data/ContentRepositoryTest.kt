package com.dicoding.jetpacksubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.jetpacksubmission.LiveDataTestUtil
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.db.LocalDataSource
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
import com.dicoding.jetpacksubmission.data.remote.RemoteDataSource
import com.dicoding.jetpacksubmission.utils.AppExecutors
import com.dicoding.jetpacksubmission.utils.DummyData
import com.dicoding.jetpacksubmission.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val contentRepository = FakeContentRepository(remote, local, appExecutors)

    private val movieResponses = DummyData.generateDummyMovieResponses()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DummyData.generateDummyTvShowResponses()
    private val tvShowId = tvShowResponses[0].id
    private val searchMovieQuery: String = "Cruella"
    private val searchTvShowQuery: String = "Loki"
    private val searchMovieResponses = DummyData.generateSearchDummyMoviesResponse(searchMovieQuery)
    private val searchTvShowResponses = DummyData.generateSearchDummyTvShowsResponse(searchTvShowQuery)


    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        contentRepository.getMovies()

        val movies =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
        verify(local).getMovies()
        assertNotNull(movies.data)
        assertEquals(movieResponses.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        contentRepository.getTvShows()

        val tvShows =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateDummyTvShows()))
        verify(local).getTvShows()
        assertNotNull(tvShows.data)
        assertEquals(tvShowResponses.size.toLong(), tvShows.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        contentRepository.getFavoriteMovie()

        val movies =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
        verify(local).getFavoriteMovie()
        assertNotNull(movies)
        assertEquals(movieResponses.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        contentRepository.getFavoriteTvShow()

        val tvShows =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateDummyTvShows()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShows)
        assertEquals(tvShowResponses.size.toLong(), tvShows.data?.size?.toLong())
    }

    @Test
    fun getSearchMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getSearchMovie(searchMovieQuery)).thenReturn(dataSourceFactory)
        contentRepository.getSearchMovie(searchMovieQuery)

        val movies =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateSearchDummyMovies(searchMovieQuery)))
        verify(local).getSearchMovie(searchMovieQuery)
        assertNotNull(movies)
        assertEquals(searchMovieResponses.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getSearchTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getSearchTvShow(searchTvShowQuery)).thenReturn(dataSourceFactory)
        contentRepository.getSearchTvShow(searchTvShowQuery)

        val movies =
            BaseResource.success(PagedListUtil.mockPagedList(DummyData.generateSearchDummyTvShows(searchTvShowQuery)))
        verify(local).getSearchTvShow(searchTvShowQuery)
        assertNotNull(movies)
        assertEquals(searchTvShowResponses.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val movieEntity = MutableLiveData<MovieEntity>()
        movieEntity.value = DummyData.generateDummyMovies()[0]
        `when`<LiveData<MovieEntity>>(local.getMovieById(movieId ?: 0)).thenReturn(movieEntity)

        val movie = LiveDataTestUtil.getValue(contentRepository.getMovieById(movieId ?: 0))
        verify(local).getMovieById(movieId ?: 0)
        assertNotNull(movie.data)
        assertEquals(movieResponses[0].title, movie.data?.title)
    }

    @Test
    fun getDetailTvShow() {
        val tvShowEntity = MutableLiveData<TvShowEntity>()
        tvShowEntity.value = DummyData.generateDummyTvShows()[0]
        `when`<LiveData<TvShowEntity>>(local.getTvShowById(tvShowId ?: 0)).thenReturn(tvShowEntity)

        val tvShow = LiveDataTestUtil.getValue(contentRepository.getTvShowById(tvShowId ?: 0))
        verify(local).getTvShowById(tvShowId ?: 0)
        assertNotNull(tvShow.data)
        assertEquals(tvShowResponses[0].title, tvShow.data?.title)
    }
}
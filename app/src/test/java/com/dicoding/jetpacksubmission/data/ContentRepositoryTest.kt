package com.dicoding.jetpacksubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.jetpacksubmission.LiveDataTestUtil
import com.dicoding.jetpacksubmission.utils.DummyData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val contentRepository = FakeContentRepository(remote)

    private val movieResponses = DummyData.generateDummyMovieResponses()
    private val tvShowResponses = DummyData.generateDummyTvShowResponses()
    private val movieResponse = DummyData.generateDummyMovieResponses()[0]
    private val tvShowResponse = DummyData.generateDummyTvShowResponses()[0]
    private val tvShowId = tvShowResponses[0].id
    private val movieId = movieResponses[0].id

    @Test
    fun getMovies() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[0] as RemoteDataSource.GetMoviesCallback).onGetMoviesSucceed(movieResponses)
                null
            }.`when`(remote).getMovies(any())
        }

        val data = LiveDataTestUtil.getValue(contentRepository.getMovies())

        runBlocking {
            verify(remote).getMovies(any())
        }

        assertNotNull(data)
        assertEquals(movieResponses.size.toLong(), data.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.GetDetailMovieCallback).onDetailMovieReceived(movieResponse)
                null
            }.`when`(remote).getDetailMovie(eq(movieId) ?: 0, any())
        }

        val data = LiveDataTestUtil.getValue(contentRepository.getDetailMovie(movieId ?: 0))

        runBlocking {
            verify(remote).getDetailMovie(eq(movieId) ?: 0, any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.id, data.id)
    }

    @Test
    fun getTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.GetTvShowsCallback).onGetTvShowsSucceed(tvShowResponses)
                null
            }.`when`(remote).getTvShows(any())
        }

        val data = LiveDataTestUtil.getValue(contentRepository.getTvShows())

        runBlocking {
            verify(remote).getTvShows(any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponses.size.toLong(), data.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.GetDetailTvShowCallback).onDetailTvShowReceived(tvShowResponse)
                null
            }.`when`(remote).getDetailTvShow(eq(tvShowId) ?: 0, any())
        }

        val data = LiveDataTestUtil.getValue(contentRepository.getDetailTvShow(tvShowId ?: 0))

        runBlocking {
            verify(remote).getDetailTvShow(eq(tvShowId) ?: 0, any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.id, data.id)
    }
}
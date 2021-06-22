package com.dicoding.jetpacksubmission.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    private val dummyMovies = DummyData.generateDummyMovies()
    private val dummyMovie = DummyData.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var listObserver: Observer<List<Content>>

    @Mock
    private lateinit var detailObserver: Observer<Content>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(contentRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Content>()
        movie.value = dummyMovie

        `when`(contentRepository.getDetailMovie(movieId)).thenReturn(movie)

        val movieData = movieViewModel.getDetailMovie(movieId).value as Content

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.title, movieData.title)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.overview, movieData.overview)
        assertEquals(dummyMovie.year, movieData.year)
        assertEquals(dummyMovie.rating, movieData.rating)
        assertEquals(dummyMovie.genre, movieData.genre)

        movieViewModel.getDetailMovie(movieId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyMovie)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<Content>>()
        movies.value = dummyMovies

        `when`(contentRepository.getMovies()).thenReturn(movies)

        val moviesData = movieViewModel.getMovies().value

        verify(contentRepository).getMovies()
        assertNotNull(moviesData)
        assertEquals(20, moviesData?.size)

        movieViewModel.getMovies().observeForever(listObserver)
        verify(listObserver).onChanged(dummyMovies)
    }
}
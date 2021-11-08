package com.dicoding.jetpacksubmission.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    private val dummyMovie = DummyData.generateDummyMovies()[0]

    private val movieId: Int = dummyMovie.id ?: 0

    private val searchQuery: String = "Cruella"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var remoteObserver: Observer<BaseResource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var localObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var detailObserver: Observer<BaseResource<MovieEntity>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(contentRepository)
        movieViewModel.setSelectedMovie(movieId)
    }

    //Start Get Movies Testing
    @Test
    fun `getMovies should be success`() {
        val movies = PagedTestDataSources.snapshot(DummyData.generateDummyMovies())
        val expected = MutableLiveData<BaseResource<PagedList<MovieEntity>>>()
        expected.value = BaseResource.success(movies)

        `when`(contentRepository.getMovies()).thenReturn(expected)

        movieViewModel.getMovies().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<BaseResource<PagedList<MovieEntity>>>()
        expected.value = BaseResource.success(movies)

        `when`(contentRepository.getMovies()).thenReturn(expected)

        movieViewModel.getMovies().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val actualValueDataSize = movieViewModel.getMovies().value?.data?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getMovies should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<BaseResource<PagedList<MovieEntity>>>()
        expected.value = BaseResource.error(expectedMessage, null)

        `when`(contentRepository.getMovies()).thenReturn(expected)

        movieViewModel.getMovies().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val actualMessage = movieViewModel.getMovies().value?.message
        assertEquals(expectedMessage, actualMessage)
    }
    //End Get Movies Testing
    
    //Start Get Favorite Movies Testing
    @Test
    fun `getFavoriteMovies should be success`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.generateDummyMovies())

        `when`(contentRepository.getFavoriteMovie()).thenReturn(expected)

        movieViewModel.getFavoriteMovies().observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getFavoriteMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavoriteMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(contentRepository.getFavoriteMovie()).thenReturn(expected)

        movieViewModel.getFavoriteMovies().observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val actualValueDataSize = movieViewModel.getFavoriteMovies().value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }
    //End Get Favorite Movies Testing

    // Start Get Search Movies Testing
    @Test
    fun `getSearchMovies should be success`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.generateSearchDummyMovies(searchQuery))

        `when`(contentRepository.getSearchMovie(searchQuery)).thenReturn(expected)

        movieViewModel.getSearchMovie(searchQuery).observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getSearchMovie(searchQuery).value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getSearchMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(contentRepository.getSearchMovie(searchQuery)).thenReturn(expected)

        movieViewModel.getSearchMovie(searchQuery).observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val actualValueDataSize = movieViewModel.getSearchMovie(searchQuery).value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }
    //End Get Search Movies Testing

    @Test
    fun `setSelectedMovie should be success`() {
        val expected = MutableLiveData<BaseResource<MovieEntity>>()
        expected.value = BaseResource.success(DummyData.generateDummyMovies()[0])

        `when`(contentRepository.getMovieById(movieId)).thenReturn(expected)

        movieViewModel.detailMovie.observeForever(detailObserver)

        verify(detailObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.detailMovie.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoriteMovie should be success trigger detailMovie observer`() {
        val expected = MutableLiveData<BaseResource<MovieEntity>>()
        expected.value = BaseResource.success(DummyData.generateDummyMovies()[0])

        `when`(contentRepository.getMovieById(movieId)).thenReturn(expected)

        movieViewModel.setFavoriteMovie()
        movieViewModel.detailMovie.observeForever(detailObserver)

        verify(detailObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.detailMovie.value

        assertEquals(expectedValue, actualValue)
    }


    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}
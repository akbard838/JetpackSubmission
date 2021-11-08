package com.dicoding.jetpacksubmission.presentation.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
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
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    private val dummyTvShow = DummyData.generateDummyTvShows()[0]

    private val tvShowId: Int = dummyTvShow.id ?: 0

    private val searchQuery: String = "Loki"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var remoteObserver: Observer<BaseResource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var localObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var detailObserver: Observer<BaseResource<TvShowEntity>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(contentRepository)
        tvShowViewModel.setSelectedTvShow(tvShowId)
    }

    //Start Get TvShows Testing
    @Test
    fun `getTvShows should be success`() {
        val tvShows = PagedTestDataSources.snapshot(DummyData.generateDummyTvShows())
        val expected = MutableLiveData<BaseResource<PagedList<TvShowEntity>>>()
        expected.value = BaseResource.success(tvShows)

        `when`(contentRepository.getTvShows()).thenReturn(expected)

        tvShowViewModel.getTvShows().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<BaseResource<PagedList<TvShowEntity>>>()
        expected.value = BaseResource.success(tvShows)

        `when`(contentRepository.getTvShows()).thenReturn(expected)

        tvShowViewModel.getTvShows().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val actualValueDataSize = tvShowViewModel.getTvShows().value?.data?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getTvShows should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<BaseResource<PagedList<TvShowEntity>>>()
        expected.value = BaseResource.error(expectedMessage, null)

        `when`(contentRepository.getTvShows()).thenReturn(expected)

        tvShowViewModel.getTvShows().observeForever(remoteObserver)
        verify(remoteObserver).onChanged(expected.value)

        val actualMessage = tvShowViewModel.getTvShows().value?.message
        assertEquals(expectedMessage, actualMessage)
    }
    //End Get TvShows Testing

    //Start Get Favorite TvShows Testing
    @Test
    fun `getFavoriteTvShows should be success`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.generateDummyTvShows())

        `when`(contentRepository.getFavoriteTvShow()).thenReturn(expected)

        tvShowViewModel.getFavoriteTvShows().observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getFavoriteTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavoriteTvShows should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(contentRepository.getFavoriteTvShow()).thenReturn(expected)

        tvShowViewModel.getFavoriteTvShows().observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val actualValueDataSize = tvShowViewModel.getFavoriteTvShows().value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }
    //End Get Favorite TvShows Testing

    // Start Get Search TvShows Testing
    @Test
    fun `getSearchTvShows should be success`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.generateSearchDummyTvShows(searchQuery))

        `when`(contentRepository.getSearchTvShow(searchQuery)).thenReturn(expected)

        tvShowViewModel.getSearchTvShow(searchQuery).observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getSearchTvShow(searchQuery).value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getSearchTvShows should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(contentRepository.getSearchTvShow(searchQuery)).thenReturn(expected)

        tvShowViewModel.getSearchTvShow(searchQuery).observeForever(localObserver)
        verify(localObserver).onChanged(expected.value)

        val actualValueDataSize = tvShowViewModel.getSearchTvShow(searchQuery).value?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }
    //End Get Search TvShows Testing

    @Test
    fun `setSelectedTvShow should be success`() {
        val expected = MutableLiveData<BaseResource<TvShowEntity>>()
        expected.value = BaseResource.success(DummyData.generateDummyTvShows()[0])

        `when`(contentRepository.getTvShowById(tvShowId)).thenReturn(expected)

        tvShowViewModel.detailTvShow.observeForever(detailObserver)

        verify(detailObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.detailTvShow.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoriteTvShow should be success trigger detailTvShow observer`() {
        val expected = MutableLiveData<BaseResource<TvShowEntity>>()
        expected.value = BaseResource.success(DummyData.generateDummyTvShows()[0])

        `when`(contentRepository.getTvShowById(tvShowId)).thenReturn(expected)

        tvShowViewModel.setFavoriteTvShow()
        tvShowViewModel.detailTvShow.observeForever(detailObserver)

        verify(detailObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.detailTvShow.value

        assertEquals(expectedValue, actualValue)
    }


    class PagedTestDataSources private constructor(private val items: List<TvShowEntity>) : PositionalDataSource<TvShowEntity>() {
        companion object {
            fun snapshot(items: List<TvShowEntity> = listOf()): PagedList<TvShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}
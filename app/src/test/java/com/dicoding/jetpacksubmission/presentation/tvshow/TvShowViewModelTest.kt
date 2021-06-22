package com.dicoding.jetpacksubmission.presentation.tvshow

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
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    private val dummyTvShows = DummyData.generateDummyTvShows()
    private val dummyTvShow = DummyData.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

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
        tvShowViewModel = TvShowViewModel(contentRepository)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<Content>()
        tvShow.value = dummyTvShow

        `when`(contentRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        val tvShowData = tvShowViewModel.getDetailTvShow(tvShowId).value as Content

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData.id)
        assertEquals(dummyTvShow.title, tvShowData.title)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.overview, tvShowData.overview)
        assertEquals(dummyTvShow.year, tvShowData.year)
        assertEquals(dummyTvShow.rating, tvShowData.rating)
        assertEquals(dummyTvShow.genre, tvShowData.genre)

        tvShowViewModel.getDetailTvShow(tvShowId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getTvShows() {
        val tvShows = MutableLiveData<List<Content>>()
        tvShows.value = dummyTvShows

        `when`(contentRepository.getTvShows()).thenReturn(tvShows)

        val dataListTvShow = tvShowViewModel.getTvShows().value

        verify(contentRepository).getTvShows()
        assertNotNull(dataListTvShow)
        assertEquals(20, dataListTvShow?.size)

        tvShowViewModel.getTvShows().observeForever(listObserver)
        verify(listObserver).onChanged(dummyTvShows)
    }
}
package com.dicoding.jetpacksubmission.presentation.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getCourses() {
        val tvShows = tvShowViewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}
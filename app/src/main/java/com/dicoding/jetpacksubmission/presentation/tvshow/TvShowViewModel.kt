package com.dicoding.jetpacksubmission.presentation.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.jetpacksubmission.utils.DummyData

class TvShowViewModel: ViewModel() {
    fun getTvShows() = DummyData.getTvShows()
}
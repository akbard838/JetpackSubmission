package com.dicoding.jetpacksubmission.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.model.Content

class TvShowViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<Content>> = contentRepository.getTvShows()

    fun getDetailTvShow(tvShowId: Int): LiveData<Content> = contentRepository.getDetailTvShow(tvShowId)

}
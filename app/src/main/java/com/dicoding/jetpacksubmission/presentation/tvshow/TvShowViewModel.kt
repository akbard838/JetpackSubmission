package com.dicoding.jetpacksubmission.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.ContentRepository2
import com.dicoding.jetpacksubmission.data.local.TvShowEntity

class TvShowViewModel(private val contentRepository: ContentRepository2) : ViewModel() {

    val tvShowId = MutableLiveData<Int>()

    var detailTvShow: LiveData<BaseResource<TvShowEntity>> = Transformations.switchMap(tvShowId) {
        contentRepository.getTvShowById(it)
    }

    fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>> = contentRepository.getTvShows()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = contentRepository.getFavoriteTvShow()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun setFavoriteTvShow() {
        val moduleResource = detailTvShow.value
        if (moduleResource != null) {
            val tvShow = moduleResource.data

            if (tvShow != null) {
                val newState = !tvShow.isFavorite
                contentRepository.setFavoriteTvShow(tvShow, newState)
            }
        }
    }

}

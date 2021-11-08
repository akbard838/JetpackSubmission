package com.dicoding.jetpacksubmission.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.data.ContentRepository
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity

class TvShowViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    val tvShowId = MutableLiveData<Int>()

    var detailTvShow: LiveData<BaseResource<TvShowEntity>> = Transformations.switchMap(tvShowId) {
        contentRepository.getTvShowById(it)
    }

    fun getTvShows(): LiveData<BaseResource<PagedList<TvShowEntity>>> = contentRepository.getTvShows()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = contentRepository.getFavoriteTvShow()

    fun getSearchTvShow(text: String): LiveData<PagedList<TvShowEntity>> = contentRepository.getSearchTvShow(text)

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun setFavoriteTvShow() {
        val tvShowResource = detailTvShow.value
        if (tvShowResource != null) {
            val tvShow = tvShowResource.data

            if (tvShow != null) {
                val newState = !tvShow.isFavorite
                contentRepository.setFavoriteTvShow(tvShow, newState)
            }
        }
    }

}

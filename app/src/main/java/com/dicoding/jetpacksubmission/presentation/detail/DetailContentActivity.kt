package com.dicoding.jetpacksubmission.presentation.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseActivity
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.presentation.ViewModelFactory
import com.dicoding.jetpacksubmission.presentation.movie.MovieViewModel
import com.dicoding.jetpacksubmission.presentation.tvshow.TvShowViewModel
import com.dicoding.jetpacksubmission.utils.*
import com.dicoding.jetpacksubmission.utils.constant.BundleKeys
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.enum.Status
import kotlinx.android.synthetic.main.activity_detail_content.*

class DetailContentActivity : BaseActivity() {

    companion object {
        fun start(context: Context, contentId: Int, contentType: String) {
            Intent(context, DetailContentActivity::class.java).apply {
                this.putExtra(BundleKeys.CONTENT_ID, contentId)
                this.putExtra(BundleKeys.CONTENT_TYPE, contentType)
                context.startActivity(this)
            }
        }
    }

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var tvShowViewModel: TvShowViewModel

    private lateinit var content: Content

    private var contentId: Int = 0

    private var contentType: String = emptyString()

    private var isButtonFavoriteClicked: Boolean = false

    override val layoutResourceId: Int = R.layout.activity_detail_content

    override fun initIntent() {
        contentId = intent.getIntExtra(BundleKeys.CONTENT_ID, 0)
        contentType = intent.getStringExtra(BundleKeys.CONTENT_TYPE) ?: emptyString()
    }

    override fun initUI() {
        setupToolbar(detailToolbar, true, emptyString())
    }

    override fun initAction() {
        fabFavorite.onClick {
            isButtonFavoriteClicked = true
            if (contentType == ContentType.MOVIE.type) movieViewModel.setFavoriteMovie()
            else tvShowViewModel.setFavoriteTvShow()
        }
    }

    override fun initProcess() {

    }

    override fun initObservable() {
        if (contentType == ContentType.MOVIE.type) {
            movieViewModel =
                ViewModelProvider(
                    this,
                    ViewModelFactory.getInstance(this)
                )[MovieViewModel::class.java]
            getMovieData()

            movieViewModel.detailMovie.observe(this, Observer { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {
                            showLoading()
                        }
                        Status.SUCCESS -> {
                            hideLoading()
                            movie.data?.let {
                                setButtonFavoriteState(it.isFavorite)
                            }
                        }
                        Status.ERROR -> {
                            hideLoading()
                            showToast(getString(R.string.message_error))
                        }
                    }
                }
            })
        } else {
            tvShowViewModel =
                ViewModelProvider(
                    this,
                    ViewModelFactory.getInstance(this)
                )[TvShowViewModel::class.java]
            getTvShowData()

            tvShowViewModel.detailTvShow.observe(this, Observer { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {
                            showLoading()
                        }
                        Status.SUCCESS -> {
                            hideLoading()
                            movie.data?.let {
                                setButtonFavoriteState(it.isFavorite)
                            }
                        }
                        Status.ERROR -> {
                            hideLoading()
                            showToast(getString(R.string.message_error))
                        }
                    }
                }
            })
        }
    }

    private fun setButtonFavoriteState(isFavorite: Boolean){
        if (isFavorite) {
            fabFavorite.setColorFilter(resources.getColor(R.color.colorRed))
            if (isButtonFavoriteClicked) showToast(getString(R.string.message_add_favorite))
        } else {
            fabFavorite.setColorFilter(resources.getColor(R.color.colorLightGrey))
            if (isButtonFavoriteClicked) showToast(getString(R.string.message_delete_favorite))
        }
        isButtonFavoriteClicked = false
    }

    private fun showLoadingUI() {
        groupContentDetail.gone()
        pbDetail.visible()
    }

    private fun hideLoadingUI() {
        groupContentDetail.visible()
        pbDetail.gone()
    }

    private fun getTvShowData() {
        tvShowViewModel.setSelectedTvShow(contentId)

        tvShowViewModel.detailTvShow.observe(this, Observer { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> {
                        showLoadingUI()
                    }
                    Status.SUCCESS -> {
                        hideLoadingUI()
                        tvShow.data?.let {
                            content = it.toContent()
                            initDetailContentUI()
                        }
                    }
                    Status.ERROR -> {
                        hideLoadingUI()
                        showCancelableAlertDialog(
                            context = this,
                            title = getString(R.string.message_error),
                            message = getString(R.string.message_detail_error),
                            positive = getString(R.string.action_retry),
                            positiveListener = {
                                getTvShowData()
                            },
                            negative = getString(R.string.action_cancel)
                        )
                    }
                }
            }
        })
    }

    private fun getMovieData() {
        movieViewModel.setSelectedMovie(contentId)

        movieViewModel.detailMovie.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        showLoadingUI()
                    }
                    Status.SUCCESS -> {
                        hideLoadingUI()
                        movie.data?.let {
                            content = it.toContent()
                            initDetailContentUI()
                        }
                    }
                    Status.ERROR -> {
                        hideLoadingUI()
                        showCancelableAlertDialog(
                            context = this,
                            title = getString(R.string.message_error),
                            message = getString(R.string.message_detail_error),
                            positive = getString(R.string.action_retry),
                            positiveListener = {
                                getMovieData()
                            },
                            negative = getString(R.string.action_cancel)
                        )
                    }
                }
            }
        })
    }

    private fun initDetailContentUI() {
        tvTitle.text = content.title
        tvReleaseDate.text = changeDate(content.year, "yyyy-MM-dd", "d MMMM yyyy")
        tvOverview.text = content.overview
        tvRating.text = String.format(getString(R.string.format_rating), content.rating.toString())
        imgPosterDetail.tag = content.poster
        imgPosterDetailBackground.tag = content.poster
        imgPosterDetail.setImagePath(
            this,
            POSTER_ENDPOINT + POSTER_SIZE_ENDPOINT_W185 + content.poster,
            pbPosterDetail,
            R.drawable.ic_broken_image
        )
        imgPosterDetailBackground.setImagePath(
            this,
            POSTER_ENDPOINT + POSTER_SIZE_ENDPOINT_W185 + content.poster,
            R.drawable.ic_broken_image
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
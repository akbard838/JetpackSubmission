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

    private var contentId: Int = 0

    private var contentType: String = emptyString()

    override val layoutResourceId: Int = R.layout.activity_detail_content

    override fun initIntent() {
        contentId = intent.getIntExtra(BundleKeys.CONTENT_ID, 0)
        contentType = intent.getStringExtra(BundleKeys.CONTENT_TYPE) ?: emptyString()
    }

    override fun initUI() {
        setupToolbar(toolbar, true, emptyString())
    }

    override fun initAction() {

    }

    override fun initProcess() {

    }

    override fun initObservable() {
        if (contentType == ContentType.MOVIE.type) {
            movieViewModel =
                ViewModelProvider(this, ViewModelFactory.getInstance())[MovieViewModel::class.java]

            getMovieData()

        } else {
            tvShowViewModel =
                ViewModelProvider(this, ViewModelFactory.getInstance())[TvShowViewModel::class.java]

            getTvShowData()
        }
    }

    private fun getTvShowData() {
        tvShowViewModel.getDetailTvShow(contentId).observe(this, Observer { tvShow ->
            if (tvShow != null) {
                initDetailContentUI(tvShow)
            } else {
                showCancelableAlertDialog(
                    context = this,
                    title = getString(R.string.title_error),
                    message = getString(R.string.message_error),
                    positive = getString(R.string.action_retry),
                    positiveListener = {
                        getTvShowData()
                    },
                    negative = getString(R.string.action_cancel)
                )
            }
        })
    }

    private fun getMovieData() {
        movieViewModel.getDetailMovie(contentId).observe(this, Observer { movie ->
            if (movie != null) {
                initDetailContentUI(movie)
            } else {
                showCancelableAlertDialog(
                    context = this,
                    title = getString(R.string.title_error),
                    message = getString(R.string.message_error),
                    positive = getString(R.string.action_retry),
                    positiveListener = {
                        getMovieData()
                    },
                    negative = getString(R.string.action_cancel)
                )
            }
        })
    }

    private fun initDetailContentUI(content: Content) {
        tvTitle.text = content.title
        tvYear.text = content.year.changeDateFormat("YYYY-MM-DD", "YYYY")
        tvOverview.text = content.overview
        tvGenre.text = getGenresContent(content)
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

    private fun getGenresContent(content: Content): String {
        val genreNames = arrayListOf<String>()
        content.genre.map { genreNames.add(it.genreName.trim()) }

        val genreNameStringBuilder = StringBuilder()
        genreNames.forEachIndexed { i, genre ->
            genreNameStringBuilder.append(genre)
            if (i != genreNames.lastIndex) {
                genreNameStringBuilder.append(", ")
            }
        }

        return genreNameStringBuilder.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
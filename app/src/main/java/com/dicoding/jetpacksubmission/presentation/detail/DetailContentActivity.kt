package com.dicoding.jetpacksubmission.presentation.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseActivity
import com.dicoding.jetpacksubmission.data.Content
import com.dicoding.jetpacksubmission.utils.constant.BundleKeys
import com.dicoding.jetpacksubmission.utils.emptyString
import com.dicoding.jetpacksubmission.utils.setImageResource
import kotlinx.android.synthetic.main.activity_detail_content.*

class DetailContentActivity : BaseActivity() {

    companion object {
        fun start(context: Context, contentId: String, contentType: String) {
            Intent(context, DetailContentActivity::class.java).apply {
                this.putExtra(BundleKeys.CONTENT_ID, contentId)
                this.putExtra(BundleKeys.CONTENT_TYPE, contentType)
                context.startActivity(this)
            }
        }
    }

    private lateinit var detailContentViewModel: DetailContentViewModel

    private var contentId: String = emptyString()

    private var contentType: String = emptyString()

    override val layoutResourceId: Int = R.layout.activity_detail_content

    override fun initIntent() {
        contentId = intent.getStringExtra(BundleKeys.CONTENT_ID) ?: emptyString()
        contentType = intent.getStringExtra(BundleKeys.CONTENT_TYPE) ?: emptyString()
    }

    override fun initUI() {
        setupToolbar(toolbar, true, emptyString())
    }

    override fun initAction() {

    }

    override fun initProcess() {
        detailContentViewModel = DetailContentViewModel()

        initDetailContentData()
    }

    override fun initObservable() {

    }

    private fun initDetailContentData() {
        if (contentId.isNotEmpty() && contentType.isNotEmpty()) {
            detailContentViewModel.setSelectedContent(contentId, contentType)
            val content = detailContentViewModel.getDetailContent()
            initDetailContentUI(content)
        }
    }

    private fun initDetailContentUI(content: Content) {
        tvTitle.text = content.title
        tvYear.text = content.year
        tvOverview.text = content.overview
        tvGenre.text = content.genre
        tvRating.text = String.format(getString(R.string.format_rating), content.rating.toString())
        imgPosterDetail.tag = content.poster
        imgPosterDetailBackground.tag = content.poster
        imgPosterDetail.setImageResource(this, content.poster)
        imgPosterDetailBackground.setImageResource(this, content.poster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
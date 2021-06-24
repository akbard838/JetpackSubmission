package com.dicoding.jetpacksubmission.presentation.favorite

import android.content.Context
import android.content.Intent
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseActivity
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.base.BasePagerAdapter
import com.dicoding.jetpacksubmission.utils.emptyString
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class FavoriteActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, FavoriteActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    override val layoutResourceId: Int = R.layout.activity_favorite

    override fun initUI() {
        setupToolbar(toolbar, true, getString(R.string.title_my_favorite))
        setViewPager()
    }

    override fun initIntent() {

    }

    override fun initAction() {

    }

    override fun initProcess() {

    }

    override fun initObservable() {

    }

    private fun setViewPager() {
        val fragmentList = listOf(FavoriteMovieFragment(),
            FavoriteTvShowFragment()
        )
        val tabTitle = listOf(getString(R.string.label_movies), getString(R.string.label_tv_shows))

        vpFavorite.adapter = BasePagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(tlFavorite, vpFavorite){ tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}
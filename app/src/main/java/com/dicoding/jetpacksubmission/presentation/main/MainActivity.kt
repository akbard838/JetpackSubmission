package com.dicoding.jetpacksubmission.presentation.main

import android.content.Context
import android.content.Intent
import com.dicoding.jetpacksubmission.base.BaseActivity
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.base.BasePagerAdapter
import com.dicoding.jetpacksubmission.presentation.favorite.FavoriteActivity
import com.dicoding.jetpacksubmission.presentation.movie.MovieFragment
import com.dicoding.jetpacksubmission.presentation.tvshow.TvShowFragment
import com.dicoding.jetpacksubmission.utils.onClick
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(this)
            }
        }
    }

    override val layoutResourceId: Int = R.layout.activity_main

    override fun initUI() {
        setViewPager()
    }

    override fun initIntent() {

    }

    override fun initAction() {
        btnMyFavorite.onClick {
            FavoriteActivity.start(this)
        }
    }

    override fun initProcess() {

    }

    override fun initObservable() {

    }

    private fun setViewPager() {
        val fragmentList = listOf<BaseFragment>(MovieFragment(),
            TvShowFragment()
        )
        val tabTitle = listOf(getString(R.string.label_movies), getString(R.string.label_tv_shows))

        vpHome.adapter = BasePagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(tlHome, vpHome){ tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}
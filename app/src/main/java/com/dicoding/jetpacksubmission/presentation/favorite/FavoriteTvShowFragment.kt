package com.dicoding.jetpacksubmission.presentation.favorite

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
import com.dicoding.jetpacksubmission.presentation.ViewModelFactory
import com.dicoding.jetpacksubmission.presentation.adapter.TvShowAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.presentation.tvshow.TvShowViewModel
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.gone
import com.dicoding.jetpacksubmission.utils.visible
import kotlinx.android.synthetic.main.fragment_tv_show.*

class FavoriteTvShowFragment : BaseFragment(), TvShowAdapter.OnTvShowItemListener {

    companion object {
        fun newInstance() =
            FavoriteTvShowFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var tvShowViewModel: TvShowViewModel

    private lateinit var tvShowAdapter: TvShowAdapter

    override val layoutResourceId: Int = R.layout.fragment_tv_show

    override fun setupIntent() {

    }

    override fun setupUI() {
        pbTvShow.visible()
        initRecyclerView()
    }

    override fun setupAction() {

    }

    override fun setupProcess() {

    }

    override fun setupObservable() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        tvShowViewModel.getFavoriteTvShows().observe(this, Observer { tvShows ->
            pbTvShow.gone()
            tvShowAdapter.submitList(tvShows)
            tvShowAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView() {
        tvShowAdapter = TvShowAdapter(requireContext(), this)

        rvTvShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    override fun onTvShowItemClicked(tvShow: TvShowEntity) {
        DetailContentActivity.start(requireContext(), tvShow.id ?: 0, ContentType.TV_SHOW.type)
    }

}
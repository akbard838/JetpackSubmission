package com.dicoding.jetpacksubmission.presentation.tvshow

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.Content
import com.dicoding.jetpacksubmission.presentation.adapter.ContentAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment: BaseFragment(), ContentAdapter.OnContentItemListener {

    companion object {
        fun newInstance() =
            TvShowFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var tvShowViewModel: TvShowViewModel

    private lateinit var contentAdapter: ContentAdapter

    override val layoutResourceId: Int = R.layout.fragment_tv_show

    override fun setupIntent() {

    }

    override fun setupUI() {
        initRecyclerView()
    }

    override fun setupAction() {

    }

    override fun setupProcess() {
        tvShowViewModel = TvShowViewModel()

        initTvShowData()
    }

    override fun setupObservable() {

    }

    private fun initTvShowData() {
        val tvShows = tvShowViewModel.getTvShows()

        contentAdapter.setData(tvShows)
    }

    private fun initRecyclerView() {
        contentAdapter = ContentAdapter(requireContext(), mutableListOf(), this)

        rvTvShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = contentAdapter
        }
    }

    override fun onContentItemClicked(content: Content) {
        DetailContentActivity.start(requireContext(), content.id, ContentType.TV_SHOW.type)
    }
}
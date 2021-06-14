package com.dicoding.jetpacksubmission.presentation.tvshow

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.presentation.ViewModelFactory
import com.dicoding.jetpacksubmission.presentation.adapter.ContentAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.showToast
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

    }

    override fun setupObservable() {
        activity?.let {
            tvShowViewModel = ViewModelProvider(
                it,
                ViewModelFactory.getInstance()
            )[TvShowViewModel::class.java]
        }

        tvShowViewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if (tvShows != null) {
                contentAdapter.setData(tvShows)
            } else {
                showToast("Oops, something error!")
            }
        })
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
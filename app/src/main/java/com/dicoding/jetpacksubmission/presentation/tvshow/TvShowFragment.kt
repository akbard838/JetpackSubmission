package com.dicoding.jetpacksubmission.presentation.tvshow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
import com.dicoding.jetpacksubmission.presentation.ViewModelFactory
import com.dicoding.jetpacksubmission.presentation.adapter.TvShowAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.enum.Status
import com.dicoding.jetpacksubmission.utils.gone
import com.dicoding.jetpacksubmission.utils.showCancelableAlertDialog
import com.dicoding.jetpacksubmission.utils.showToast
import com.dicoding.jetpacksubmission.utils.visible
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : BaseFragment(), TvShowAdapter.OnTvShowItemListener {

    companion object {
        fun newInstance() =
            TvShowFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var tvShowAdapter: TvShowAdapter

    private lateinit var tvShowViewModel: TvShowViewModel

    override val layoutResourceId: Int = R.layout.fragment_tv_show

    override fun setupIntent() {

    }

    override fun setupUI() {
        edtTvShow.visible()
        initRecyclerView()
    }

    override fun setupAction() {
        edtTvShow.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                tvShowAdapter.submitList(null)
                tvShowViewModel.getSearchTvShow("$text%").observe(viewLifecycleOwner, Observer { movies ->
                    tvShowAdapter.submitList(movies)
                    tvShowAdapter.notifyDataSetChanged()
                })
            }
        })
    }

    override fun setupProcess() {

    }

    override fun setupObservable() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        getTvShowsData()
    }

    private fun initRecyclerView() {
        tvShowAdapter = TvShowAdapter(requireContext(), this)

        rvTvShow.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun getTvShowsData() {
        tvShowViewModel.getTvShows().observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {
                        pbTvShow.visible()
                    }
                    Status.SUCCESS -> {
                        pbTvShow.gone()
                        tvShowAdapter.submitList(response.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        pbTvShow.gone()
                        showCancelableAlertDialog(
                            context = requireContext(),
                            title = getString(R.string.message_error),
                            message = getString(R.string.message_tv_show_error),
                            positive = getString(R.string.action_retry),
                            positiveListener = { getTvShowsData() },
                            negative = getString(R.string.action_cancel)
                        )
                        showToast(getString(R.string.message_error))
                    }
                }
            }
        })
    }

    override fun onTvShowItemClicked(tvShow: TvShowEntity) {
        DetailContentActivity.start(requireContext(), tvShow.id ?: 0, ContentType.TV_SHOW.type)
    }

}
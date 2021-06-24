package com.dicoding.jetpacksubmission.presentation.movie

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.local.MovieEntity
import com.dicoding.jetpacksubmission.presentation.ViewModelFactory2
import com.dicoding.jetpacksubmission.presentation.adapter.MovieAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.enum.Status
import com.dicoding.jetpacksubmission.utils.gone
import com.dicoding.jetpacksubmission.utils.showCancelableAlertDialog
import com.dicoding.jetpacksubmission.utils.showToast
import com.dicoding.jetpacksubmission.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseFragment(), MovieAdapter.OnMovieItemListener {

    companion object {
        fun newInstance() =
            MovieFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var movieAdapter: MovieAdapter

    private lateinit var movieViewModel: MovieViewModel

    override val layoutResourceId: Int = R.layout.fragment_movie

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
        val factory = ViewModelFactory2.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        getMoviesData()
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(requireContext(), this)

        rvMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun getMoviesData() {
        movieViewModel.getMovies().observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.LOADING -> {
                        pbMovie.visible()
                    }
                    Status.SUCCESS -> {
                        pbMovie.gone()
                        movieAdapter.submitList(response.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        pbMovie.gone()
                        showCancelableAlertDialog(
                            context = requireContext(),
                            title = getString(R.string.message_error),
                            message = getString(R.string.message_movie_error),
                            positive = getString(R.string.action_retry),
                            positiveListener = { getMoviesData() },
                            negative = getString(R.string.action_cancel)
                        )
                        showToast(getString(R.string.message_error))
                    }
                }
            }
        })
    }

    override fun onMovieItemClicked(movie: MovieEntity) {
        DetailContentActivity.start(requireContext(), movie.id ?: 0, ContentType.MOVIE.type)
    }

}
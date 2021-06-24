package com.dicoding.jetpacksubmission.presentation.favorite

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
import com.dicoding.jetpacksubmission.presentation.movie.MovieViewModel
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import com.dicoding.jetpacksubmission.utils.gone
import com.dicoding.jetpacksubmission.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*

class FavoriteMovieFragment : BaseFragment(), MovieAdapter.OnMovieItemListener {

    companion object {
        fun newInstance() =
            FavoriteMovieFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var movieAdapter: MovieAdapter

    override val layoutResourceId: Int = R.layout.fragment_movie

    override fun setupIntent() {

    }

    override fun setupUI() {
        pbMovie.visible()
        initRecyclerView()
    }

    override fun setupAction() {

    }

    override fun setupProcess() {

    }

    override fun setupObservable() {
        val factory = ViewModelFactory2.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieViewModel.getFavoriteMovies().observe(this, Observer { movies ->
            pbMovie.gone()
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(requireContext(), this)

        rvMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onMovieItemClicked(movie: MovieEntity) {
        DetailContentActivity.start(requireContext(), movie.id ?: 0, ContentType.MOVIE.type)
    }

}
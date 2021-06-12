package com.dicoding.jetpacksubmission.presentation.movie

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseFragment
import com.dicoding.jetpacksubmission.data.Content
import com.dicoding.jetpacksubmission.presentation.adapter.ContentAdapter
import com.dicoding.jetpacksubmission.presentation.detail.DetailContentActivity
import com.dicoding.jetpacksubmission.utils.enum.ContentType
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : BaseFragment(), ContentAdapter.OnContentItemListener {

    companion object {
        fun newInstance() =
            MovieFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var contentAdapter: ContentAdapter

    override val layoutResourceId: Int = R.layout.fragment_movie

    override fun setupIntent() {

    }

    override fun setupUI() {
        initRecyclerView()
    }

    override fun setupAction() {
    }

    override fun setupProcess() {
        movieViewModel = MovieViewModel()

        initMovieData()
    }

    override fun setupObservable() {

    }

    private fun initMovieData() {
        val movies = movieViewModel.getMovies()

        contentAdapter.setData(movies)
    }

    private fun initRecyclerView() {
        contentAdapter = ContentAdapter(requireContext(), mutableListOf(), this)

        rvMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = contentAdapter
        }
    }

    override fun onContentItemClicked(content: Content) {
        DetailContentActivity.start(requireContext(), content.id, ContentType.MOVIE.type)
    }

}
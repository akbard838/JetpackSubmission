package com.dicoding.jetpacksubmission.presentation.movie

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

    }

    override fun setupObservable() {
        activity?.let {
            movieViewModel = ViewModelProvider(
                it,
                ViewModelFactory.getInstance()
            )[MovieViewModel::class.java]
        }

        movieViewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                contentAdapter.setData(movies)
            } else {
                showToast("Oops, something error!")
            }
        })
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
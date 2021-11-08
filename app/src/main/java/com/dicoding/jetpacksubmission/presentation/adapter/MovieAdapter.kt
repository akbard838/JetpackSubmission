package com.dicoding.jetpacksubmission.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.utils.*
import kotlinx.android.synthetic.main.item_content.view.*

class MovieAdapter(
    private val context: Context?,
    private val listener: MovieAdapter.OnMovieItemListener? = null
) :
    PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_content, parent, false))

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            with(movieViewHolder.itemView) {
                tvTitle.text = data.title
                tvYear.text = changeDate(data.year.orEmpty(), "yyyy-MM-dd", "YYYY")
                tvOverview.text = data.overview
                tvRating.text =
                    String.format(context.getString(R.string.format_rating), data.rating.toString())
                imgPoster.setImagePath(
                    context,
                    POSTER_ENDPOINT + POSTER_SIZE_ENDPOINT_W185 + data.poster,
                    pbImgPoster,
                    R.drawable.ic_broken_image
                )

                this.onClick {
                    listener?.onMovieItemClicked(data)
                }
            }
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.tvTitle
        val year: TextView = itemView.tvYear
        val overview: TextView = itemView.tvOverview
        val rating: TextView = itemView.tvRating
        val poster: ImageView = itemView.imgPoster
    }

    interface OnMovieItemListener {
        fun onMovieItemClicked(movie: MovieEntity)
    }
}


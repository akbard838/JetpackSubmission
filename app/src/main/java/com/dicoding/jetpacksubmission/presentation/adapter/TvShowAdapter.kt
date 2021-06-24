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
import com.dicoding.jetpacksubmission.data.local.TvShowEntity
import com.dicoding.jetpacksubmission.utils.*
import kotlinx.android.synthetic.main.item_content.view.*

class TvShowAdapter(
    private val context: Context?,
    private val listener: TvShowAdapter.OnTvShowItemListener? = null
) :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_content, parent, false))

    override fun onBindViewHolder(TvShowViewHolder: TvShowViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            with(TvShowViewHolder.itemView) {
                tvTitle.text = data.title
                tvYear.text = data.year?.changeDateFormat("YYYY-MM-DD", "YYYY")
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
                    listener?.onTvShowItemClicked(data)
                }
            }
        }
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.tvTitle
        val year: TextView = itemView.tvYear
        val overview: TextView = itemView.tvOverview
        val rating: TextView = itemView.tvRating
        val poster: ImageView = itemView.imgPoster
    }

    interface OnTvShowItemListener {
        fun onTvShowItemClicked(TvShow: TvShowEntity)
    }
}


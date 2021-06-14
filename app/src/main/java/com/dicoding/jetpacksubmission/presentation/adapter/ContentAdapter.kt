package com.dicoding.jetpacksubmission.presentation.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseRecycleViewAdapter
import com.dicoding.jetpacksubmission.data.model.Content
import com.dicoding.jetpacksubmission.utils.*
import kotlinx.android.synthetic.main.item_content.view.*

class ContentAdapter(
    context: Context,
    data: MutableList<Content> = mutableListOf(),
    private val listener: OnContentItemListener? = null
) : BaseRecycleViewAdapter<Content>(context, data) {

    override val layoutResourceId: Int = R.layout.item_content

    override fun bindView(holder: RecyclerView.ViewHolder, data: Content, position: Int) {
        with(holder.itemView){
            tvTitle.text = data.title
            tvYear.text = data.year.changeDateFormat("YYYY-MM-DD", "YYYY")
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
                listener?.onContentItemClicked(data)
            }
        }
    }

    fun setData(list: List<Content>) {
        if (data.size > 0) {
            data.clear()
        }
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: Content) {
        val i: Int = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyDataSetChanged()
        } else {
            data.add(item)
            notifyDataSetChanged()
        }
    }

    interface OnContentItemListener {
        fun onContentItemClicked(content: Content)
    }
}
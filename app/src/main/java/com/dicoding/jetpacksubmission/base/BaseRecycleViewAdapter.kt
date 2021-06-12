package com.dicoding.jetpacksubmission.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T>(
    val context: Context,
    val data: MutableList<T>
) : RecyclerView.Adapter<BaseRecycleViewAdapter<T>.BaseViewHolder>() {

    abstract val layoutResourceId: Int

    abstract fun bindView(holder: RecyclerView.ViewHolder, data: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(context).inflate(
                layoutResourceId,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindView(holder as RecyclerView.ViewHolder, data[position], position)
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    open fun add(item: T?) {
        if (item != null) {
            this.data.add(item)
        }
        notifyItemInserted(this.data.size - 1)
    }

    open fun addAll(items: List<T>?) {
        if (items != null) {
            this.add(items)
        }
    }

    open fun add(item: T?, position: Int) {
        if (item != null) {
            this.data.add(position, item)
        }
        notifyItemInserted(position)
    }

    open fun add(items: List<T?>) {
        val size = items.size
        for (i in 0 until size) {
            items[i]?.let { this.data.add(it) }
        }
        notifyDataSetChanged()
    }

    open fun addOrUpdate(items: List<T?>) {
        val size = items.size
        for (i in 0 until size) {
            val item: T? = items[i]
            val x: Int = this.data.indexOf(item)
            if (x >= 0) {
                if (item != null) {
                    this.data[x] = item
                }
            } else {
                this.add(item)
            }
        }
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        if (position >= 0 && position < this.data.size) {
            this.data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }
}


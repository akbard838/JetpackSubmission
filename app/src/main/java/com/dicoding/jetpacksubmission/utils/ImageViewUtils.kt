package com.dicoding.jetpacksubmission.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageResource(c: Context, imageRes: Int) {
    Glide.with(c)
        .load(imageRes)
        .into(this)
}
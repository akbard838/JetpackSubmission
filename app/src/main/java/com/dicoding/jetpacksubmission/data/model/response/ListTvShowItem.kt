package com.dicoding.jetpacksubmission.data.model.response

import com.google.gson.annotations.SerializedName

data class ListTvShowItem(
    @SerializedName("results")
    val results: List<TvShowItem>?
)
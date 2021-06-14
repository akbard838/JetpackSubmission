package com.dicoding.jetpacksubmission.data.model.response

import com.google.gson.annotations.SerializedName

data class ListMovieItem(
    @SerializedName("results")
    val results: List<MovieItem>?
)
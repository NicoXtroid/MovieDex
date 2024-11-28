package com.example.moviedex.models

import com.google.gson.annotations.SerializedName

data class DataMovieList(
    @SerializedName("movieList")val movieList: List<Movie>
)

package com.example.moviedex.network.response

import com.example.moviedex.models.Movie
import com.google.gson.annotations.SerializedName

data class ResponseMovieList(
    @SerializedName("results") var responseMoviesList: List<Movie>
)

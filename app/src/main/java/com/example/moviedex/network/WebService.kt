package com.example.moviedex.network

import com.example.moviedex.network.response.ResponseMovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<ResponseMovieList>

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Response<ResponseMovieList>

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Response<ResponseMovieList>
}
package com.example.moviedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedex.core.Constants
import com.example.moviedex.models.DataMovieList
import com.example.moviedex.models.Movie
import com.example.moviedex.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel : ViewModel() {

    private var movieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> = movieList


    private var topMovieList = MutableLiveData<List<Movie>>()
    val topMoviesList: LiveData<List<Movie>> = movieList

    private var upcomingMovieList = MutableLiveData<List<Movie>>()
    val upcomingMoviesList: LiveData<List<Movie>> = movieList

    fun getAllMovies(){
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                movieList.value =
                    response.body()!!.responseMoviesList.sortedBy { (it.popularity).toFloat() }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getTopRatedMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                topMovieList.value =
                    response.body()!!.responseMoviesList.sortedByDescending { (it.voteAverage).toFloat() }
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getUpcomingMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                upcomingMovieList.value =
                    response.body()!!.responseMoviesList.sortedByDescending { it.releaseDate }
            }
        }
    }
}
package com.example.moviedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedex.core.Constants
import com.example.moviedex.models.Movie
import com.example.moviedex.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel:ViewModel() {

    private var movieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> = movieList


    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.responseMoviesList.sortedBy { (it.popularity).toFloat() }
            }
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.responseMoviesList.sortedByDescending { (it.vote_average).toFloat() }
            }
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieList.value = response.body()!!.responseMoviesList.sortedByDescending { it.release_date }
            }
        }
    }
}
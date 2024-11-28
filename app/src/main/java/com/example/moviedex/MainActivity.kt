package com.example.moviedex

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedex.databinding.ActivityMainBinding
import com.example.moviedex.ui.MoviesAdapter
import com.example.moviedex.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: MoviesAdapter
    private lateinit var btnGetPopular: Button
    private lateinit var btnGetTopRated: Button
    private lateinit var btnGetUpcoming: Button
    private lateinit var tvCategory: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        btnGetPopular = findViewById(R.id.btnGetPopular)
        btnGetTopRated = findViewById(R.id.btnGetTopRated)
        btnGetUpcoming = findViewById(R.id.btnGetUpcoming)
        tvCategory = findViewById(R.id.tvCategory)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]


        viewModel.getAllMovies()

        tvCategory.text = "POPULAR"
        //viewModel.getPopularMovies()
        viewModel.moviesList.observe(this){
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()
        }

        viewModel.getTopRatedMovies()
        viewModel.topMoviesList.observe(this){
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()
        }

        viewModel.getUpcomingMovies()
        viewModel.upcomingMoviesList.observe(this){
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovies.layoutManager = layoutManager
        adapterMovies = MoviesAdapter(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovies
    }
}
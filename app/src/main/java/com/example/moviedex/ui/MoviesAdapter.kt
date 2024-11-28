package com.example.moviedex.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedex.R
import com.example.moviedex.core.Constants
import com.example.moviedex.models.Movie

class MoviesAdapter(
    val context: Context,
    var moviesList: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvMovie = itemView.findViewById(R.id.cvMovie) as CardView
        val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
        val tvInfo = itemView.findViewById(R.id.tvInfo) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(context)
            .load("${Constants.API_IMAGE_URL}${movie.posterPath}")
            .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
            .into(holder.ivImage)

        holder.tvInfo.text = HtmlCompat.fromHtml(
            "<b>Title:</b> " +movie.title +
            "<br><b>Poularity:</b> "+ movie.popularity+
            "<br><b>Rating:</b> "+ movie.voteAverage,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        holder.cvMovie.setOnClickListener{
            showOverview(movie.title, movie.overview)
        }
    }

    private fun showOverview(title: String, overview: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(overview)
        builder.show()
    }
}
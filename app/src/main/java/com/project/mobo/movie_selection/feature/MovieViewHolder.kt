package com.project.mobo.movie_selection.feature

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
    val view: View = view
    val img_movie: ImageView = view.findViewById(R.id.img_movie)
    val txt_movie: TextView = view.findViewById(R.id.txt_movie)
    val img_rating_movie: ImageView = view.findViewById(R.id.img_rating_movie)
    val txt_rating_movie: TextView = view.findViewById(R.id.txt_rating_movie)

    fun bindTop(data: MovieItem){
        img_movie.setImageResource(data.img_movie)
        txt_movie.text = data.name
        txt_movie.setTextSize(15F)
        img_rating_movie.layoutParams.width = 220
        img_rating_movie.layoutParams.height = 50
        txt_rating_movie.setTextSize(10F)

        view.requestLayout()
    }
    fun bind(data: MovieItem){
        img_movie.setImageResource(data.img_movie)
        txt_movie.text = data.name
        img_rating_movie.setImageResource(data.rating_star)
    }
}
package com.project.mobo.movie_selection.feature

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem
import kotlin.math.log

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val view: View = view
    var img_movie: ImageView = view.findViewById(R.id.img_movie)
    val txt_movie: TextView = view.findViewById(R.id.txt_movie)
    val img_rating_movie: ImageView = view.findViewById(R.id.img_rating_movie)
    val img_check: ImageView = view.findViewById(R.id.img_check)

    fun bind(data: MovieItem) {
        img_movie.setImageResource(data.img_movie)
        txt_movie.text = data.name
        img_rating_movie.setImageResource(data.rating_star)
        if(data.isSelected){
            img_movie.alpha = 0.25f
            img_check.visibility = View.VISIBLE
        }
        else if (!data.isSelected){
            img_movie.alpha = 1f
            img_check.visibility = View.INVISIBLE
        }
    }
}
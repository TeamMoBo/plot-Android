package com.project.mobo.movie_selection.feature

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem
import kotlin.math.log

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val view: View = view
    var img_movie: ImageView = view.findViewById(R.id.img_movie)
    val txt_movie: TextView = view.findViewById(R.id.txt_movie)
    val img_rating_movie: ImageView = view.findViewById(R.id.img_rating_movie)
    var txt_rating_movie: TextView = view.findViewById(R.id.txt_rating_movie)
    val img_check: ImageView = view.findViewById(R.id.img_check)

    fun bind(data: MovieItem) {
        Glide.with(view).load(data.img_movie).into(img_movie)
        img_movie.setClipToOutline(true)
        txt_movie.text = data.name

        txt_rating_movie.setText("${data.rating_star}/10")
        //점수 별
        if (data.rating_star >= 9f){
            img_rating_movie.setImageResource(R.drawable.img_star_5)
        }
        else if (data.rating_star >= 7f){
            img_rating_movie.setImageResource(R.drawable.img_star_4)
        }
        else if (data.rating_star >= 5f){
            img_rating_movie.setImageResource(R.drawable.img_star_3)
        }
        else if (data.rating_star >= 3f){
            img_rating_movie.setImageResource(R.drawable.img_star_2)
        }
        else if (data.rating_star >= 1f){
            img_rating_movie.setImageResource(R.drawable.img_star_1)
        }
        else{
            img_rating_movie.setImageResource(R.drawable.img_star_0)
        }

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
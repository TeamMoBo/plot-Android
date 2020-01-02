package com.project.mobo.main_page.choice_movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.temp.ReserveMovie

class ChoiceMovieAdapter(private val context: Context, private var reserveMovie: ArrayList<ReserveMovie>) : RecyclerView.Adapter<ChoiceMovieViewHolder>(){

    //var data = listOf<ChoiceMovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceMovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_choice_item, parent, false)

        return ChoiceMovieViewHolder(view)
    }

    override fun getItemCount(): Int = reserveMovie.size
        //return data.size


    override fun onBindViewHolder(holder: ChoiceMovieViewHolder, position: Int) {
        holder.bind(reserveMovie[position])
    }
}
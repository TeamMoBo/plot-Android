package com.project.mobo.main_page.choice_movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class choiceMovieAdapter(private val context: Context) : RecyclerView.Adapter<choiceMovieViewHolder>(){

    var data = listOf<choiceMovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): choiceMovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_choice_item, parent, false)

        return choiceMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: choiceMovieViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
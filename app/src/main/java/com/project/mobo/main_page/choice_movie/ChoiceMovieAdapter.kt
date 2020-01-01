package com.project.mobo.main_page.choice_movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChoiceMovieAdapter(private val context: Context) : RecyclerView.Adapter<ChoiceMovieViewHolder>(){

    var data = listOf<ChoiceMovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceMovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_choice_item, parent, false)

        return ChoiceMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChoiceMovieViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
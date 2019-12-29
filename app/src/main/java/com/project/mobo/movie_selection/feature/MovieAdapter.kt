package com.project.mobo.movie_selection.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>(){
    var data = listOf<MovieItem>()
    var isTop: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (isTop){
            holder.bindTop(data[position])
        }
        else{
            holder.bind(data[position])
        }
    }
}
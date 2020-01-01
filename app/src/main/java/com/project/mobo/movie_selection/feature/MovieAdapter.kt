package com.project.mobo.movie_selection.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem

class MovieAdapter(rv_item: Int): RecyclerView.Adapter<MovieViewHolder>(){
    var data = arrayListOf<MovieItem>()
    private var onItemClickListener : View.OnClickListener? = null
    val rv_item = rv_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rv_item, parent, false)
        view.setOnClickListener(onItemClickListener)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun onItemClick(l: View.OnClickListener){
        onItemClickListener = l
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            holder.bind(data[position])
    }
}
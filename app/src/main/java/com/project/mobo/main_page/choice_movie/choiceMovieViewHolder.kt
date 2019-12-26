package com.project.mobo.Main.Choice_Movie

import android.content.ClipData
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mobo.R
import kotlinx.android.synthetic.main.movie_choice_item.view.*

class choiceMovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val ItemImage : ImageView=view.findViewById(R.id.imgMainItemPoster)
    val tvItemTitle : TextView =view.findViewById(R.id.tvMainItemTitle)
    val tvItemStar : TextView=view.findViewById(R.id.tvMainItemStar)

    fun bind(data: choiceMovieItem){
        Glide.with(itemView)
            .load(R.drawable.an_background)
            .into(ItemImage)
        tvItemTitle.text=data.title
        tvItemStar.text=data.star

    }
}
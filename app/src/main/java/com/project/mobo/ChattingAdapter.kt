package com.project.mobo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChattingAdapter(private val context: Context) : RecyclerView.Adapter<ChattingViewHolder>() {
    var data = listOf<ChattingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.rv_my_chatting,parent,false)
        return ChattingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChattingViewHolder, position: Int) {
        holder.bind(data[position])
    }


}
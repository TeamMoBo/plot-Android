package com.project.mobo.main_page.choice_date

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class choiceDateAdapter(private val context: Context) : RecyclerView.Adapter<choiceDateViewHolder>() {

    var data = listOf<choiceDateItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): choiceDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.time_choice_item, parent, false)

        return choiceDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: choiceDateViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
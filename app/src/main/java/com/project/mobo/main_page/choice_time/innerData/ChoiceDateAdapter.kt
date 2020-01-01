package com.project.mobo.main_page.choice_time.innerData

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.temp.ReserveDate

class ChoiceDateAdapter(private val context: Context, private var reserveDate : ArrayList<Int>) : RecyclerView.Adapter<ChoiceDateViewHolder>() {

    //var data = listOf<ChoiceDateItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.time_choice_item, parent, false)

        return ChoiceDateViewHolder(view)
    }

    override fun getItemCount(): Int = reserveDate.size

    override fun onBindViewHolder(holder: ChoiceDateViewHolder, position: Int) {
        holder.bind(reserveDate[position])
    }
}
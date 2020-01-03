package com.project.mobo.MatchingHistory

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mobo.MatchingDetailedActivity
import com.project.mobo.R
import com.project.mobo.api.HistoryData

class MatchingHistoryViewAdapter(val ctx : Context) : RecyclerView.Adapter<MatchingHistoryViewAdapter.Holder>(){
    var historydata = arrayListOf<HistoryData>()
    private var onItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_match_list, parent, false)
        view.setOnClickListener(onItemClickListener)
        return Holder(view)
    }
    //val ItemImageState: RecyclerView =ctx.findViewById(R.id.rvMainDay)

    override fun getItemCount(): Int = historydata.size

    fun onItemClick(l: View.OnClickListener){
        onItemClickListener = l
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = historydata[position].date
        holder.movie_title.text = historydata[position].movieTitle
        Glide.with(ctx)
            .load(historydata[position].img)
            .into(holder.profile_img)
        holder.name.text = historydata[position].name
        holder.age.text = historydata[position].age.toString()
        holder.kakaoId.text = historydata[position].kakaotalk

        var state : Boolean = historydata[position].state
        if(state){
            holder.txtStatus.text="진행중"
            holder.txtStatus.setTextColor(Color.parseColor("#58d07a"))
            holder.viewState.setBackgroundColor(Color.parseColor("#58d07a"))
        } else {
            holder.txtStatus.text="완료됨"
            holder.txtStatus.setTextColor(Color.parseColor("#9e9e9e"))
            holder.viewState.setBackgroundColor(Color.parseColor("#9e9e9e"))
        }

        var imageIdx : Int = historydata[position].matchingIdx // matchingIdx 값 받아온 것

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val date = itemView.findViewById(R.id.txtMatchingDate) as TextView
        val movie_title = itemView.findViewById(R.id.txtMatchingMovieName) as TextView
        val profile_img = itemView.findViewById(R.id.imgMatchingProfile) as ImageView
        val name = itemView.findViewById(R.id.txtMAtchingName) as TextView
        val age = itemView.findViewById(R.id.txtMAtchingAge) as TextView
        val kakaoId  = itemView.findViewById(R.id.txtMAtchingKaKaoId) as TextView
        val txtStatus = itemView.findViewById(R.id.txtMatchingStatus) as TextView
        val viewState = itemView.findViewById(R.id.viewMatchingStatus) as View
    }
}
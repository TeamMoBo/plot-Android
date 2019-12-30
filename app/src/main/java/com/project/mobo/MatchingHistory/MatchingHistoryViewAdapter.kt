package com.project.mobo.MatchingHistory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mobo.R

class MatchingHistoryViewAdapter(val ctx : Context, val dataList : ArrayList<MatchingHistoryData>) : RecyclerView.Adapter<MatchingHistoryViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_match_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.date.text = dataList[position].date
        holder.movie_title.text = dataList[position].movie_title
        Glide.with(ctx)
            .load(dataList[position].profile_img)
            .into(holder.profile_img)
        holder.name.text = dataList[position].name
        holder.age.text = dataList[position].age.toString()
        holder.kakaoId.text = dataList[position].kakaoId

        holder.item_btn.setOnClickListener {
            //ctx.startActivity<MatchingDetailedActivity>()
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val date = itemView.findViewById(R.id.txtMatchingDate) as TextView
        val movie_title = itemView.findViewById(R.id.txtMatchingMovieName) as TextView
        val profile_img = itemView.findViewById(R.id.imgMatchingProfile) as ImageView
        val name = itemView.findViewById(R.id.txtMAtchingName) as TextView
        val age = itemView.findViewById(R.id.txtMAtchingAge) as TextView
        val kakaoId  = itemView.findViewById(R.id.txtMAtchingKaKaoId) as TextView

        val item_btn = itemView.findViewById(R.id.rv_match_list_item) as ConstraintLayout
    }
}
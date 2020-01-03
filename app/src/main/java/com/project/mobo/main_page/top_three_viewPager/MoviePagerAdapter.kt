package com.project.mobo.main_page.top_three_viewPager

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.project.mobo.R
import com.project.mobo.api.RandMovie
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.movie_choice_item.view.*
import kotlinx.android.synthetic.main.time_choice_item.view.*

class MoviePagerAdapter(private val list: ArrayList<RandMovie>): PagerAdapter() {

    var data = listOf<MovieData>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        //val view = inflater.inflate(R.layout.tab_main_page, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        //view.imgMainFirstPoster.text=list[position].photo
        Glide.with(view)
            .load(list[position].movieImg)
            .into(view.imgMainFirstPoster)

        view.imgMainFirstPoster.setClipToOutline(true)

        view.tvItemTitle.text = list[position].movieName

        Log.v("moviscore", list[position].movieScore.toString())
        Log.v("Position", position.toString())

        view.tvMainFirstStar.text=list[position].movieScore.toString()
        view.tvMainFirstHour.text=list[position].movieRuntime
        view.tvViewPagerGenre.text=list[position].movieGenre

        var url : String = list[position].movieIdx.toString()
        //view.ivItem.setImageResource(list[position].getImageId(container.context))
        //iew.tvItemContent.text = list[position].content
        view.btnMainFirstPlay.setOnClickListener(){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            //startActivity(i)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }
}
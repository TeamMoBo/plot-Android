package com.project.mobo.main_page.top_three_viewPager

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.project.mobo.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.movie_choice_item.view.*
import kotlinx.android.synthetic.main.time_choice_item.view.*

class MoviePagerAdapter(private val list: ArrayList<MovieData>): PagerAdapter() {

    var data = listOf<MovieData>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        //val view = inflater.inflate(R.layout.tab_main_page, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        //GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.main_viewpager_corner)

        //view.setBackground(drawable);
        //view.setClipToOutline(true);

        //view.imgMainFirstPoster.text=list[position].photo
        Glide.with(view)
            .load(list[position].photo)
            .into(view.imgMainFirstPoster)
        view.tvItemTitle.text = list[position].title
        //view.tvMainItemStar.text=list[position].star
        //view.tvMainItemTime.text=list[position].hour
        //view.ivItem.setImageResource(list[position].getImageId(container.context))
        //view.tvItemContent.text = list[position].content

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
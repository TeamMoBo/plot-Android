package com.project.mobo.movie_selection.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem
import com.project.mobo.movie_selection.feature.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment(), View.OnClickListener {
    private lateinit var rv_movie3: RecyclerView
    private lateinit var rv_movie4: RecyclerView
    public var movieAdapter3 = MovieAdapter(R.layout.rv_item_movie3)
    public var movieAdapter4 = MovieAdapter(R.layout.rv_item_movie4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_fragment_two, container, false)
        //rv1
        rv_movie3 = v.findViewById(R.id.rv_movie3)
        rv_movie3.setHasFixedSize(true)

        movieAdapter3.onItemClick(this)

        rv_movie3.adapter = movieAdapter3

        rv_movie3.layoutManager = GridLayoutManager(activity,2)

        movieAdapter3.notifyDataSetChanged()

        //rv2
        rv_movie4 = v.findViewById(R.id.rv_movie4)
        rv_movie4.setHasFixedSize(true)

        movieAdapter4.onItemClick(this)

        rv_movie4.adapter = movieAdapter4

        rv_movie4.layoutManager = GridLayoutManager(activity,4)

        movieAdapter4.notifyDataSetChanged()

        // Inflate the layout for this fragment
        return v
    }

    override fun onClick(v: View) {
        if(v.parent == rv_movie3) {
            val item = movieAdapter3.data[rv_movie3.getChildAdapterPosition(v)]
            click_event(item, movieAdapter3)
        }
        else if(v.parent == rv_movie4){
            val item2 = movieAdapter4.data[rv_movie4.getChildAdapterPosition(v)]
            click_event(item2, movieAdapter4)
        }
    }

    fun click_event(item: MovieItem, adapter: RecyclerView.Adapter<MovieViewHolder>){
        if(item.isSelected){
            item.isSelected = false
        }
        else if(!item.isSelected){
            item.isSelected = true
        }
        adapter.notifyDataSetChanged()
    }
}
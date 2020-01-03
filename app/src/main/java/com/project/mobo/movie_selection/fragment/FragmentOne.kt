package com.project.mobo.movie_selection.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem
import com.project.mobo.movie_selection.feature.MovieAdapter
import com.project.mobo.movie_selection.feature.MovieViewHolder

/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment(), View.OnClickListener {
    private lateinit var rv_movie1: RecyclerView
    private lateinit var rv_movie2: RecyclerView
    var movieAdapter1: MovieAdapter = MovieAdapter(R.layout.rv_item_movie1)
    var movieAdapter2: MovieAdapter = MovieAdapter(R.layout.rv_item_movie2)

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_fragment_one, container, false)
        //rv1
        rv_movie1 = v.findViewById(R.id.rv_movie1)
        rv_movie1.setHasFixedSize(true)

        movieAdapter1.onItemClick(this)

        rv_movie1.adapter = movieAdapter1

        rv_movie1.layoutManager = GridLayoutManager(activity,2)


        movieAdapter1.notifyDataSetChanged()

        //rv2
        rv_movie2 = v.findViewById(R.id.rv_movie2)
        rv_movie2.setHasFixedSize(true)

        movieAdapter2.onItemClick(this)

        rv_movie2.adapter = movieAdapter2

        rv_movie2.layoutManager = GridLayoutManager(activity,4)

        movieAdapter2.notifyDataSetChanged()

        // Inflate the layout for this fragmen
        return v
    }

    override fun onClick(v: View?) {
        if(v?.parent == rv_movie1) {
            val item = movieAdapter1.data[rv_movie1.getChildAdapterPosition(v)]
            click_event(item, movieAdapter1)
        }
        else if(v?.parent == rv_movie2){
            val item2 = movieAdapter2.data[rv_movie2.getChildAdapterPosition(v)]
            click_event(item2, movieAdapter2)
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